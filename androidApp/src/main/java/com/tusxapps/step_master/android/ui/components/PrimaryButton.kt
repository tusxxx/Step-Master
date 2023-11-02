package com.tusxapps.step_master.android.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tusxapps.step_master.android.ui.theme.largeDp
import com.tusxapps.step_master.android.ui.theme.mediumDp

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        Modifier
            .fillMaxWidth()
            .then(modifier),
        shape = RoundedCornerShape(mediumDp),
        contentPadding = PaddingValues(largeDp)
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