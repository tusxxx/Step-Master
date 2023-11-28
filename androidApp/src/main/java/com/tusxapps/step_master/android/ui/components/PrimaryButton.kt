package com.tusxapps.step_master.android.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import com.tusxapps.step_master.android.ui.theme.largeDp
import com.tusxapps.step_master.android.ui.theme.mediumDp

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    shape: Shape = RoundedCornerShape(mediumDp),
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    Button(
        modifier = modifier,
        shape = shape,
        contentPadding = PaddingValues(largeDp),
        onClick = onClick
    ) {
        Text(
            text, style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun PrimaryButtonPreview() {
    PrimaryButton(text = "Заре", onClick = { })
}