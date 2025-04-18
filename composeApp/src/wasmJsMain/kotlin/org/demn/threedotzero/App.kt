package org.demn.threedotzero

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class Type(
	val name: String,
)

val possibleTypes = listOf(Type("ST"), Type("PT"), Type("RF"), Type("VG"))

@Composable
fun App(
	receive: (b: Int, m: Int, type: String) -> Unit
) {
	MaterialTheme {
		var bConstant by remember { mutableStateOf("") }
		var mConstant by remember { mutableStateOf("") }

		var dropdownExpanded by remember { mutableStateOf(false) }

		var selectedType by remember { mutableStateOf(possibleTypes.first()) }

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
						onValueChange = { if (it.toIntOrNull() != null) bConstant = it },
						placeholder = { Text("B constant") },
					)

					OutlinedTextField(
						value = mConstant,
						onValueChange = { if (it.toIntOrNull() != null) mConstant = it },
						placeholder = { Text("M constant") },
					)

					Box {
						TextButton(
							onClick = { dropdownExpanded = !dropdownExpanded },
							modifier = Modifier
						) {
							Text("Selected Type: ${selectedType.name}")
						}

						DropdownMenu(
							expanded = dropdownExpanded,
							onDismissRequest = { dropdownExpanded = false },
							modifier = Modifier
						) {
							possibleTypes.forEach { type ->
								DropdownMenuItem(onClick = {
									selectedType = type
									dropdownExpanded = false
								}) {
									Text(text = type.name)
								}
							}
						}
					}

					Button(
						onClick = {
							receive(
								bConstant.toInt(),
								mConstant.toInt(),
								selectedType.name,
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