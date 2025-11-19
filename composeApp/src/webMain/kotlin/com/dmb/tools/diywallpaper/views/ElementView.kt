package com.dmb.tools.diywallpaper.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dmb.tools.diywallpaper.models.WallpaperElement

@Composable
fun ElementView(
    modifier: Modifier = Modifier,
    element: WallpaperElement,
    onChange: (WallpaperElement) -> Unit
) {
    Column(modifier = modifier.padding(vertical = 8.dp, horizontal = 16.dp)) {
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
            OutlinedTextField(
                modifier = Modifier.width(100.dp),
                value = element.index.toString(),
                onValueChange = {
                    onChange(element.copy(index = try {
                        it.toInt()
                    } catch (e: Exception) {
                        e.printStackTrace()
                        0
                    }))
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
    }
}