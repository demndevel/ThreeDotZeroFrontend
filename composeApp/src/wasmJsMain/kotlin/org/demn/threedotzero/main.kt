package org.demn.threedotzero

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
	val serverUrl = "https://someserver.com"

	val api = createApi(serverUrl)
	val scope = CoroutineScope(EmptyCoroutineContext)

	ComposeViewport(document.body!!) {
		var uiState: AppUiState by remember { mutableStateOf(AppUiState.Ok) }

		App(
			receive = { b: Int, m: Int ->
				scope.launch {
					val result = api.receiveData(
						b = b,
						m = m,
					)

					when (result) {
						"-1" -> {
							uiState = AppUiState.MIsTooSmall
						}

						"-2" -> {
							uiState = AppUiState.BIsTooSmall
						}

						else -> {
							window.location.href = "$serverUrl:9000/$result"
						}
					}
				}
			},
			uiState = uiState,
		)
	}
}