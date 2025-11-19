package com.dmb.tools.diywallpaper.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import com.dmb.tools.diywallpaper.models.WallpaperElement

@Composable
fun ElementView(
    modifier: Modifier = Modifier,
    element: WallpaperElement,
    onDelete: () -> Unit,
    onChange: (WallpaperElement) -> Unit
) {
    var expanded by remember { mutableStateOf(true) }

    Card(modifier = modifier.padding(vertical = 8.dp, horizontal = 16.dp)) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = if (expanded) "Collapse" else "Expand"
                )
                Spacer(Modifier.width(16.dp))
                Text(
                    text = "${element.id} (${element.index})",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = onDelete) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete Element")
                }
            }

            AnimatedVisibility(visible = expanded) {
                Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                    HorizontalDivider()
                    Spacer(Modifier.height(8.dp))
                    Row {
                        OutlinedTextField(
                            modifier = Modifier.width(200.dp),
                            value = element.id,
                            onValueChange = {
                                onChange(element.copy(id = it))
                            },
                            label = {
                                Text("Id")
                            }
                        )
                        Spacer(Modifier.width(16.dp))
                        IntInput(
                            modifier = Modifier.width(100.dp),
                            value = element.index,
                            onValueChange = {
                                onChange(element.copy(index = it))
                            },
                            label = {
                                Text("Index")
                            }
                        )
                    }

                    ContentSourceView(
                        modifier = Modifier.fillMaxWidth(),
                        contentSource = element.contentSource,
                        onChanged = {
                            onChange(element.copy(
                                contentSource = it
                            ))
                        }
                    )

                    HorizontalDivider(Modifier.padding(vertical = 8.dp))

                    TransformView(
                        modifier = Modifier.fillMaxWidth(),
                        transform = element.transform,
                        onChanged = {
                            onChange(element.copy(transform = it))
                        }
                    )

                    HorizontalDivider(Modifier.padding(vertical = 8.dp))

                    AnimationView(
                        modifier = Modifier.fillMaxWidth(),
                        animations = element.animations,
                        onChanged = {
                            onChange(element.copy(animations = it))
                        }
                    )
                    Spacer(Modifier.height(8.dp))
                }
            }
        }
    }
}