package com.tusxapps.step_master.android.ui.main.profile.settings.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tusxapps.step_master.android.R
import com.tusxapps.step_master.android.ui.components.PrimaryTextField
import com.tusxapps.step_master.android.ui.components.SmallSpacer
import com.tusxapps.step_master.android.ui.theme.mediumDp
import com.tusxapps.step_master.android.ui.theme.shadowColor
import com.tusxapps.step_master.android.ui.theme.smallDp

@Composable
fun PasswordSettingsTextField(
    value: String,
    hint: String,
    modifier: Modifier = Modifier,
    label: String? = null,
    canBeEdited: Boolean = true,
    onValueChange: (String) -> Unit,
    errorMessage: String? = null
) {
    var passwordVisible by remember { mutableStateOf(false) }
    val color = animateColorAsState(
        targetValue =
        if (errorMessage != null)
            MaterialTheme.colorScheme.error
        else if (canBeEdited)
            MaterialTheme.colorScheme.primary
        else
            Color.Transparent,
        label = "textFieldBorder"
    )

    Column(modifier = modifier) {
        label?.let {
            Text(
                text = label,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start = smallDp)
            )
            SmallSpacer()
        }
        TextField(
            value = if (canBeEdited) value else "",
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = if (canBeEdited) {
                        hint
                    } else {
                        stringResource(
                            R.string.click_edit_to_change_password
                        )
                    },
                    color = Color.LightGray
                )
            },
            shape = RoundedCornerShape(mediumDp),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 4.dp,
                    spotColor = shadowColor,
                    shape = RoundedCornerShape(mediumDp)
                )
                .background(Color.White, RoundedCornerShape(mediumDp))
                .border(
                    width = 1.dp,
                    color = color.value,
                    shape = RoundedCornerShape(mediumDp)
                )
                .focusProperties { canFocus = canBeEdited },
            colors = TextFieldDefaults.colors(
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                if (canBeEdited) {
                    val icon = if (passwordVisible) {
                        R.drawable.ic_eye_open
                    } else {
                        R.drawable.ic_eye_close
                    }
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(painter = painterResource(id = icon), null)
                    }
                }
            },
            singleLine = true
        )
        AnimatedVisibility(visible = errorMessage != null) {
            Text(
                text = errorMessage ?: "",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(start = smallDp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun PrimaryTextFieldPreview() {
    var value by remember { mutableStateOf("") }

    PrimaryTextField(
        value = value,
        hint = "Hint",
        onValueChange = { value = it },
        icon = R.drawable.ic_eye_open
    )
}