package com.dmb.tools.diywallpaper.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import com.dmb.tools.diywallpaper.models.WallpaperTransform

@Composable
fun TransformView(modifier: Modifier = Modifier, transform: WallpaperTransform, onChanged: (WallpaperTransform) -> Unit) {
    Column(modifier = modifier) {
        Text("Position")
        Spacer(Modifier.height(4.dp))
        SizeGroup(text = "Position X", size = transform.positionX) {
            onChanged(transform.copy(positionX = it))
        }
        Spacer(Modifier.height(4.dp))
        SizeGroup(text = "Position Y", size = transform.positionY) {
            onChanged(transform.copy(positionY = it))
        }
        Spacer(Modifier.height(8.dp))
        Text("Scale")
        Spacer(Modifier.height(4.dp))
        FloatInput(
            label = { Text("Scale") },
            value = transform.scaleX,
            onValueChange = {
                onChanged(transform.copy(scaleX = it))
            }
        )
        Slider(
            value = transform.scaleX,
            onValueChange = { onChanged(transform.copy(scaleX = it)) },
            valueRange = 0.1f..3f
        )
        Spacer(Modifier.height(8.dp))
        Text("Rotation")
        Spacer(Modifier.height(4.dp))
        FloatInput(
            label = { Text("Rotation") },
            value = transform.rotation,
            onValueChange = {
                onChanged(transform.copy(rotation = it))
            }
        )
        Slider(
            value = transform.rotation,
            onValueChange = { onChanged(transform.copy(rotation = it)) },
            valueRange = 0f..360f
        )
        Spacer(Modifier.height(8.dp))
        Text("Pivot")
        Spacer(Modifier.height(4.dp))
        Row(Modifier.fillMaxWidth()) {
            Column(Modifier.weight(1f)) {
                Text("Pivot X")
                SizeGroup(text = "Pivot X", size = transform.pivotX) {
                    onChanged(transform.copy(pivotX = it))
                }
            }
            Column(Modifier.weight(1f)) {
                Text("Pivot Y")
                SizeGroup(text = "Pivot Y", size = transform.pivotY) {
                    onChanged(transform.copy(pivotY = it))
                }
            }
        }
    }
}
