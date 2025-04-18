package org.demn.threedotzero

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import io.ktor.client.utils.*
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
	val api = createApi()
	val scope = CoroutineScope(EmptyCoroutineContext)

	scope.launch {
		api.receiveData(3, 3, "a")
	}

	ComposeViewport(document.body!!) {
		App(
			receive = { b: Int, m: Int, type: String ->
				scope.launch {
					val result = api.receiveData(
						b = b,
						m = m,
						type = type,
					)

					window.location.href = result
				}
			}
		)
	}
}