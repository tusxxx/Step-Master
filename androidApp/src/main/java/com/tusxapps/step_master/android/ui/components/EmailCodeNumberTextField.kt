package com.tusxapps.step_master.android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tusxapps.step_master.android.ui.theme.extraLargeDp

@Composable
fun EmailCodeNumberTextField(
    value: String,
    onValueChange: (String) -> Unit
) {
    val pattern = remember { Regex("^[^\\t]*\$") }
    val text = remember { mutableStateOf(value) }
    val shape = RoundedCornerShape(extraLargeDp)
    val maxChar = 1
    val focusManager = LocalFocusManager.current

    LaunchedEffect(text.value) {
        if (text.value.isNotEmpty()) {
            focusManager.moveFocus(
                focusDirection = FocusDirection.Next,
            )
        }
    }

    TextField(
        value = text.value,
        onValueChange = {
            if (it.length <= maxChar && (it.isEmpty() || pattern.matches(it))) {
                text.value = it
            }
            onValueChange(it)
        },
        shape = shape,
        modifier = Modifier
            .width(59.dp)
            .height(74.dp)
            .border(2.dp, Color.LightGray, RoundedCornerShape(extraLargeDp))
            .background(Color.White, shape = shape)
            .onKeyEvent {
                if (it.key == Key.Tab) {
                    focusManager.moveFocus(FocusDirection.Next)
                    true
                }
                if (text.value.isEmpty() && it.key == Key.Backspace) {
                    focusManager.moveFocus(FocusDirection.Previous)
                }
                false
            },
        colors = TextFieldDefaults.colors(
            disabledTextColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White
        ),
        textStyle = LocalTextStyle.current.copy(
            fontSize = 34.sp,
            fontWeight = MaterialTheme.typography.headlineLarge.fontWeight,
            textAlign = TextAlign.Center
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next
        ),
        singleLine = true
    )
}