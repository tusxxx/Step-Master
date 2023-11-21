package com.tusxapps.step_master.android.ui.auth.email_confirmation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tusxapps.step_master.android.ui.auth.email_confirmation.components.EmailCodeNumberTextField

@Composable
fun CodeNumberFields(
    firstValue: String,
    secondValue: String,
    thirdValue: String,
    fourthValue: String,
    fifthValue: String,
    onFirstValueChange: (String) -> Unit,
    onSecondValueChange: (String) -> Unit,
    onThirdValueChange: (String) -> Unit,
    onFourthValueChange: (String) -> Unit,
    onFifthValueChange: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        EmailCodeNumberTextField(value = firstValue, onValueChange = onFirstValueChange)
        EmailCodeNumberTextField(value = secondValue, onValueChange = onSecondValueChange)
        EmailCodeNumberTextField(value = thirdValue, onValueChange = onThirdValueChange)
        EmailCodeNumberTextField(value = fourthValue, onValueChange = onFourthValueChange)
        EmailCodeNumberTextField(value = fifthValue, onValueChange = onFifthValueChange)
    }
}