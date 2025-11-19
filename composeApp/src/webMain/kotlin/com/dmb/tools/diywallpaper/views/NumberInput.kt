package com.dmb.tools.diywallpaper.views

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun IntInput(
    value: Int,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    var text by remember { mutableStateOf(value.toString()) }
    var lastValidValue by remember { mutableStateOf(value) }

    if (value != lastValidValue) {
        text = value.toString()
        lastValidValue = value
    }

    OutlinedTextField(
        value = text,
        onValueChange = { newText ->
            text = newText
            val newValue = newText.toIntOrNull()
            if (newValue != null) {
                lastValidValue = newValue
                onValueChange(newValue)
            }
        },
        modifier = modifier,
        label = label,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Composable
fun FloatInput(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    var text by remember { mutableStateOf(value.toString()) }
    var lastValidValue by remember { mutableStateOf(value) }

    if (value != lastValidValue) {
        text = value.toString()
        lastValidValue = value
    }

    OutlinedTextField(
        value = text,
        onValueChange = { newText ->
            text = newText
            val newValue = newText.toFloatOrNull()
            if (newValue != null) {
                lastValidValue = newValue
                onValueChange(newValue)
            }
        },
        modifier = modifier,
        label = label,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
    )
}

@Composable
fun LongInput(
    value: Long,
    onValueChange: (Long) -> Unit,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    var text by remember { mutableStateOf(value.toString()) }
    var lastValidValue by remember { mutableStateOf(value) }

    if (value != lastValidValue) {
        text = value.toString()
        lastValidValue = value
    }

    OutlinedTextField(
        value = text,
        onValueChange = { newText ->
            text = newText
            val newValue = newText.toLongOrNull()
            if (newValue != null) {
                lastValidValue = newValue
                onValueChange(newValue)
            }
        },
        modifier = modifier,
        label = label,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}
