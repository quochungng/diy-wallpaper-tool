package com.dmb.tools.diywallpaper.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dmb.tools.diywallpaper.models.ContentSource

@Composable
fun ContentSourceView(
    modifier: Modifier,
    contentSource: ContentSource,
    onChanged: (ContentSource) -> Unit
) {
    Column(
        modifier = modifier,
    ) {
        Spacer(Modifier.height(16.dp))
        Text("Content Source")
        Spacer(Modifier.height(4.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            var selectingType by remember { mutableStateOf(false) }
            DropdownMenu(
                expanded = selectingType,
                onDismissRequest = {
                    selectingType = false
                }
            ) {
                DropdownMenuItem(
                    text = {
                        Text("Image")
                    },
                    onClick = {
                        onChanged(ContentSource.Image(""))
                        selectingType = false
                    }
                )
                DropdownMenuItem(
                    text = {
                        Text("Text")
                    },
                    onClick = {
                        onChanged(ContentSource.Text(""))
                        selectingType = false
                    }
                )
            }
            OutlinedButton(onClick = {
                selectingType = true
            }) {
                Text(
                    when (contentSource) {
                        is ContentSource.Text -> "Text"
                        is ContentSource.Image -> "Image"
                    }
                )
                Spacer(Modifier.width(4.dp))
                Icon(Icons.Default.ArrowDownward, "Down")
            }
            Spacer(Modifier.width(16.dp))
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = when (contentSource) {
                    is ContentSource.Text -> contentSource.text
                    is ContentSource.Image -> contentSource.url
                },
                onValueChange = {
                    when (contentSource) {
                        is ContentSource.Image -> onChanged(contentSource.copy(it))
                        is ContentSource.Text -> onChanged(contentSource.copy(it))
                    }
                },
                label = {
                    Text("Source")
                }
            )
            if (contentSource is ContentSource.Image) {
                Spacer(Modifier.width(16.dp))
                Button(onClick = {

                }) {
                    Text("Upload")
                }
            }
        }
    }
}