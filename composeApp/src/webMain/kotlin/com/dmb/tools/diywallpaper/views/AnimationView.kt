package com.dmb.tools.diywallpaper.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dmb.tools.diywallpaper.models.Animation
import com.dmb.tools.diywallpaper.models.UnitType
import com.dmb.tools.diywallpaper.models.UnitValue

@Composable
fun AnimationView(
    modifier: Modifier = Modifier,
    animations: List<Animation>,
    onChanged: (List<Animation>) -> Unit
) {
    Column(modifier = modifier) {
        Text("Animations")
        Spacer(Modifier.height(8.dp))
        
        animations.forEachIndexed { index, animation ->
            AnimationItemView(
                animation = animation,
                onDelete = {
                    val newList = animations.toMutableList()
                    newList.removeAt(index)
                    onChanged(newList)
                },
                onChanged = { newAnimation ->
                    val newList = animations.toMutableList()
                    newList[index] = newAnimation
                    onChanged(newList)
                }
            )
            Spacer(Modifier.height(8.dp))
        }

        var showAddDropdown by remember { mutableStateOf(false) }
        Button(onClick = { showAddDropdown = true }) {
            Icon(Icons.Default.Add, contentDescription = "Add Animation")
            Text("Add Animation")
        }
        DropdownMenu(
            expanded = showAddDropdown,
            onDismissRequest = { showAddDropdown = false }
        ) {
            DropdownMenuItem(
                text = { Text("Translation") },
                onClick = {
                    val newAnim = Animation.Translation(
                        fromX = UnitValue.Value(0f, UnitType.PX),
                        toX = UnitValue.Value(100f, UnitType.PX),
                        fromY = UnitValue.Value(0f, UnitType.PX),
                        toY = UnitValue.Value(100f, UnitType.PX),
                        duration = 1000,
                        repeatCount = 0
                    )
                    onChanged(animations + newAnim)
                    showAddDropdown = false
                }
            )
            DropdownMenuItem(
                text = { Text("Rotation") },
                onClick = {
                    val newAnim = Animation.Rotation(
                        fromAngle = 0f,
                        toAngle = 360f,
                        pivotX = UnitValue.Value(0.5f, UnitType.RATIO),
                        pivotY = UnitValue.Value(0.5f, UnitType.RATIO),
                        duration = 1000,
                        repeatCount = 0
                    )
                    onChanged(animations + newAnim)
                    showAddDropdown = false
                }
            )
        }
    }
}

@Composable
fun AnimationItemView(
    animation: Animation,
    onDelete: () -> Unit,
    onChanged: (Animation) -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = when (animation) {
                        is Animation.Translation -> "Translation"
                        is Animation.Rotation -> "Rotation"
                    },
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = onDelete) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete")
                }
            }
            
            Row {
                LongInput(
                    modifier = Modifier.weight(1f),
                    value = animation.duration,
                    onValueChange = {
                        val newAnim = when (animation) {
                            is Animation.Translation -> animation.copy(duration = it)
                            is Animation.Rotation -> animation.copy(duration = it)
                        }
                        onChanged(newAnim)
                    },
                    label = { Text("Duration (ms)") }
                )
                Spacer(Modifier.width(8.dp))
                IntInput(
                    modifier = Modifier.weight(1f),
                    value = animation.repeatCount,
                    onValueChange = {
                        val newAnim = when (animation) {
                            is Animation.Translation -> animation.copy(repeatCount = it)
                            is Animation.Rotation -> animation.copy(repeatCount = it)
                        }
                        onChanged(newAnim)
                    },
                    label = { Text("Repeat Count") }
                )
            }
            
            Spacer(Modifier.height(8.dp))
            
            when (animation) {
                is Animation.Translation -> {
                    SizeGroup(text = "From X", size = animation.fromX) {
                        onChanged(animation.copy(fromX = it))
                    }
                    SizeGroup(text = "To X", size = animation.toX) {
                        onChanged(animation.copy(toX = it))
                    }
                    SizeGroup(text = "From Y", size = animation.fromY) {
                        onChanged(animation.copy(fromY = it))
                    }
                    SizeGroup(text = "To Y", size = animation.toY) {
                        onChanged(animation.copy(toY = it))
                    }
                }
                is Animation.Rotation -> {
                    Row {
                        FloatInput(
                            modifier = Modifier.weight(1f),
                            value = animation.fromAngle,
                            onValueChange = {
                                onChanged(animation.copy(fromAngle = it))
                            },
                            label = { Text("From Angle") }
                        )
                        Spacer(Modifier.width(8.dp))
                        FloatInput(
                            modifier = Modifier.weight(1f),
                            value = animation.toAngle,
                            onValueChange = {
                                onChanged(animation.copy(toAngle = it))
                            },
                            label = { Text("To Angle") }
                        )
                    }
                    Spacer(Modifier.height(8.dp))
                    SizeGroup(text = "Pivot X", size = animation.pivotX) {
                        onChanged(animation.copy(pivotX = it))
                    }
                    SizeGroup(text = "Pivot Y", size = animation.pivotY) {
                        onChanged(animation.copy(pivotY = it))
                    }
                }
            }
        }
    }
}
