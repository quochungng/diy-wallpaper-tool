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
        OutlinedTextField(
            label = { Text("Scale") },
            value = transform.scaleX.toString(),
            onValueChange = {
                onChanged(transform.copy(scaleX = try { it.toFloat() } catch (_: Exception) { 1f }))
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
        OutlinedTextField(
            label = { Text("Rotation") },
            value = transform.rotation.toString(),
            onValueChange = {
                onChanged(transform.copy(rotation = try { it.toFloat() } catch (_: Exception) { 0f }))
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
//        Row(Modifier.fillMaxWidth()) {
//            Column(Modifier.weight(1f)) {
//                Text("Pivot X")
//                Slider(
//                    value = transform.pivotX,
//                    onValueChange = { onChanged(transform.copy(pivotX = it)) },
//                    valueRange = 0f..1f
//                )
//            }
//            Column(Modifier.weight(1f)) {
//                Text("Pivot Y")
//                Slider(
//                    value = transform.pivotY,
//                    onValueChange = { onChanged(transform.copy(pivotY = it)) },
//                    valueRange = 0f..1f
//                )
//            }
//        }
    }
}
