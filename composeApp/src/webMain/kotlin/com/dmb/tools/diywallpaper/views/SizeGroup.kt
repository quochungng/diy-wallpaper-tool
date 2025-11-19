package com.dmb.tools.diywallpaper.views

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dmb.tools.diywallpaper.models.UnitType
import com.dmb.tools.diywallpaper.models.UnitValue

@Composable
fun SizeGroup(text: String, size: UnitValue, onValueChange: (UnitValue) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text)
        Spacer(Modifier.width(4.dp))
        Button(
            onClick = {
                onValueChange(
                    UnitValue.Add(
                        a = size,
                        b = UnitValue.Value(0f, UnitType.DP)
                    ),
                )
            }
        ) {
            Text("+")
        }
        Spacer(Modifier.width(4.dp))
        Button(
            onClick = {
                onValueChange(
                    UnitValue.Subtract(
                        a = size,
                        b = UnitValue.Value(0f, UnitType.DP)
                    ),
                )
            }
        ) {
            Text("-")
        }
    }
    Spacer(Modifier.height(4.dp))
    Row(
        modifier = Modifier.fillMaxWidth().horizontalScroll(
            state = rememberScrollState(),
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        UnitValueView(size) {
            onValueChange(it)
        }
    }
}
