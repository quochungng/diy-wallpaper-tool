package com.dmb.tools.diywallpaper.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dmb.tools.diywallpaper.models.UnitType
import com.dmb.tools.diywallpaper.models.UnitValue

@Composable
fun UnitValueView(unitValue: UnitValue, onDelete: (() -> Unit)? = null, onChanged: (UnitValue) -> Unit) {
    when (unitValue) {
        is UnitValue.Value -> {
            FloatInput(
                modifier = Modifier.width(200.dp),
                value = unitValue.value,
                label = { Text("Value") },
                leadingIcon = {
                    var showDropdown by remember { mutableStateOf(false) }
                    DropdownMenu(
                        expanded = showDropdown,
                        onDismissRequest = { showDropdown = false }
                    ) {
                        DropdownMenuItem(text = {
                            Text("Dp")
                        }, onClick = {
                            onChanged(
                                unitValue.copy(
                                    unitType = UnitType.DP
                                )
                            )
                            showDropdown = false
                        })
                        DropdownMenuItem(text = {
                            Text("Ratio")
                        }, onClick = {
                            onChanged(
                                unitValue.copy(
                                    unitType = UnitType.RATIO
                                )
                            )
                            showDropdown = false
                        })
                        DropdownMenuItem(text = {
                            Text("Pixel")
                        }, onClick = {
                            onChanged(
                                unitValue.copy(
                                    unitType = UnitType.PX
                                )
                            )
                            showDropdown = false
                        })
                    }
                    Text(
                        modifier = Modifier.clickable { showDropdown = true },
                        text = when (unitValue.unitType) {
                            UnitType.DP -> "Dp"
                            UnitType.RATIO -> "Rat"
                            UnitType.PX -> "Px"
                        }
                    )
                },
                trailingIcon = if (onDelete != null) {
                    {
                        IconButton(onClick = onDelete) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete icon")
                        }
                    }
                } else null,
                onValueChange = {
                    onChanged(
                        unitValue.copy(
                            value = it
                        )
                    )
                }
            )
        }

        is UnitValue.Add -> {
            UnitValueView(unitValue.a, onDelete = {
                onChanged(unitValue.b)
            }, onChanged = {
                onChanged(unitValue.copy(a = it, b = unitValue.b))
            })
            Text(" + ")
            UnitValueView(unitValue.b, onDelete = {
                onChanged(unitValue.a)
            }, onChanged = {
                onChanged(unitValue.copy(a = unitValue.a, b = it))
            })
        }

        is UnitValue.Subtract -> {
            UnitValueView(unitValue.a, onDelete = {
                onChanged(unitValue.b)
            }, onChanged = {
                onChanged(unitValue.copy(a = it, b = unitValue.b))
            })
            Text(" - ")
            UnitValueView(unitValue.b, onDelete = {
                onChanged(unitValue.a)
            }, onChanged = {
                onChanged(unitValue.copy(a = unitValue.a, b = it))
            })
        }

        is UnitValue.Multiple -> {
            UnitValueView(unitValue.a, onDelete = {
                onChanged(unitValue.a)
            }, onChanged = {
                onChanged(unitValue.copy(a = it))
            })
            Text(" * ")
            FloatInput(
                modifier = Modifier.width(100.dp),
                value = unitValue.b,
                onValueChange = {
                    onChanged(unitValue.copy(b = it))
                },
                label = { Text("Factor") }
            )
        }

        is UnitValue.Divide -> {
            UnitValueView(unitValue.a, onDelete = {
                onChanged(unitValue.a)
            }, onChanged = {
                onChanged(unitValue.copy(a = it))
            })
            Text(" / ")
            FloatInput(
                modifier = Modifier.width(100.dp),
                value = unitValue.b,
                onValueChange = {
                    onChanged(unitValue.copy(b = it))
                },
                label = { Text("Divisor") }
            )
        }
    }
}
