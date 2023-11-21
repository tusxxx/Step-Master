package com.tusxapps.step_master.android.ui.main.summary.components

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.tusxapps.step_master.android.ui.theme.editTextFieldColor
import com.tusxapps.step_master.android.ui.theme.mediumDp
import com.tusxapps.step_master.android.ui.theme.shadowColor

@Composable
fun EditDialogTextField(
    modifier: Modifier = Modifier,
    value: String,
    hint: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = hint,
                style = MaterialTheme.typography.labelSmall,
                color = Color.LightGray
            )
        },
        shape = RoundedCornerShape(mediumDp),
        modifier = modifier
            .shadow(
                elevation = 4.dp,
                spotColor = shadowColor,
                shape = RoundedCornerShape(mediumDp)
            )
            .background(editTextFieldColor, RoundedCornerShape(mediumDp)),
        colors = TextFieldDefaults.colors(
            disabledTextColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedContainerColor = editTextFieldColor,
            unfocusedContainerColor = editTextFieldColor
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true
    )
}