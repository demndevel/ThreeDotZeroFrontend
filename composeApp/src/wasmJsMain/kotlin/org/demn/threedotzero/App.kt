package org.demn.threedotzero

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

//data class Type(
//	val name: String,
//)
//
//val possibleTypes = listOf(Type("ST"), Type("PT"), Type("RF"), Type("VG"))

sealed interface AppUiState {
	data object Ok : AppUiState

	data object BIsTooSmall : AppUiState

	data object MIsTooSmall : AppUiState
}

@Composable
fun App(
	receive: (b: Int, m: Int) -> Unit,
	uiState: AppUiState,
) {
	MaterialTheme {
		var bConstant by remember { mutableStateOf("") }
		var mConstant by remember { mutableStateOf("") }

		var dropdownExpanded by remember { mutableStateOf(false) }

//		var selectedType by remember { mutableStateOf(possibleTypes.first()) }

		Column(
			horizontalAlignment = Alignment.CenterHorizontally,
			modifier = Modifier
				.fillMaxWidth()
		) {
			Text(
				text = "Университет Сириус – Хакатон 3.0",
				style = MaterialTheme.typography.h3,
				modifier = Modifier
			)

			Spacer(Modifier.height(30.dp))

			Card(
				modifier = Modifier
					.width(300.dp)
			) {
				Column(
					horizontalAlignment = Alignment.CenterHorizontally,
					verticalArrangement = Arrangement.spacedBy(8.dp),
					modifier = Modifier
						.fillMaxWidth()
						.padding(16.dp)
				) {
					OutlinedTextField(
						value = bConstant,
						onValueChange = { if (it.toIntOrNull() != null || it.isEmpty()) bConstant = it },
						placeholder = { Text("B constant") },
						isError = uiState == AppUiState.BIsTooSmall,
					)

					if (uiState == AppUiState.BIsTooSmall) {
						Text(
							text = "b is too small",
							style = MaterialTheme.typography.body2,
							color = MaterialTheme.colors.error,
						)
					}

					OutlinedTextField(
						value = mConstant,
						onValueChange = { if (it.toIntOrNull() != null || it.isEmpty()) mConstant = it },
						placeholder = { Text("M constant") },
						isError = uiState == AppUiState.MIsTooSmall,
					)

					if (uiState == AppUiState.MIsTooSmall) {
						Text(
							text = "m is too small",
							style = MaterialTheme.typography.body2,
							color = MaterialTheme.colors.error,
						)
					}

					Button(
						onClick = {
							if (bConstant.toIntOrNull() == null || mConstant.toIntOrNull() == null) return@Button

							receive(
								bConstant.toInt(),
								mConstant.toInt(),
							)
						},
						modifier = Modifier
							.fillMaxWidth()
					) {
						Text("Receive")
					}
				}
			}
		}
	}
}