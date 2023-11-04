package com.tusxapps.step_master.android.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CheckboxWithText(
    isChecked: Boolean,
    text: String,
    modifier: Modifier = Modifier,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = isChecked, onCheckedChange = onCheckedChange)
        Text(text = text, style = MaterialTheme.typography.labelMedium)
    }
}