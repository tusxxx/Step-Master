package com.tusxapps.step_master.android.ui.main.settings.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tusxapps.step_master.android.R
import com.tusxapps.step_master.android.ui.components.SmallSpacer
import com.tusxapps.step_master.android.ui.theme.mediumDp
import com.tusxapps.step_master.android.ui.theme.shadowColor
import com.tusxapps.step_master.android.ui.theme.smallDp

@Composable
fun ProfileTextField(
    value: String,
    hint: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    canBeEdited: Boolean = true,
    @DrawableRes icon: Int? = null,
    errorMessage: String? = null
) {
    val painter = if (icon != null) painterResource(icon) else null
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
        Text(
            text = hint,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(start = smallDp)
        )
        SmallSpacer()
        TextField(
            value = value,
            onValueChange = onValueChange,
            shape = RoundedCornerShape(mediumDp),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 4.dp,
                    spotColor = shadowColor,
                    shape = RoundedCornerShape(mediumDp)
                )
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(mediumDp)
                )
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
            trailingIcon = painter?.let {
                {
                    Icon(
                        painter = it, contentDescription = null
                    )
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
private fun ProfileTextFieldPreview() {
    var value by remember { mutableStateOf("") }

    ProfileTextField(
        value = value,
        hint = "Hint",
        onValueChange = { value = it },
        icon = R.drawable.ic_eye_open
    )
}