package com.tusxapps.step_master.android.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.tusxapps.step_master.utils.isEmailValid

@Composable
fun EmailTextField(value: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier) {
    var isError by remember { mutableStateOf(false) }
    LaunchedEffect(value) {
        isError = !value.isEmailValid() && value.isNotBlank()
    }
    PrimaryTextField(
        value = value,
        hint = "Email",
        onValueChange = onValueChange,
        modifier = modifier,
        errorMessage = "Неверный формат почты".takeIf { isError }
    )
}