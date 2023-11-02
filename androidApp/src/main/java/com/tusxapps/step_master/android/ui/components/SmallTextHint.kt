package com.tusxapps.step_master.android.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SmallTextHint() {
    Text(
        text = "Мы вышлем Вам код для подтверждения почты",
        style = MaterialTheme.typography.labelSmall
    )
}