package com.dmb.tools.diywallpaper.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun SettingsDialog(
    onDismiss: () -> Unit,
    onSave: (String) -> Unit,
    initialToken: String
) {
    var token by remember { mutableStateOf(initialToken) }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "Settings",
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(Modifier.height(16.dp))

                Text("General", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(8.dp))
                
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = token,
                    onValueChange = { token = it },
                    label = { Text("Bearer Token") },
                    singleLine = true
                )

                // Add more settings here in the future
                Spacer(Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Cancel")
                    }
                    Spacer(Modifier.width(8.dp))
                    Button(onClick = { onSave(token) }) {
                        Text("Save")
                    }
                }
            }
        }
    }
}
