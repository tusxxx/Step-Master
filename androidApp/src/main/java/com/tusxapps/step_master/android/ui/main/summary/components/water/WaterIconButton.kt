package com.tusxapps.step_master.android.ui.main.summary.components.water

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tusxapps.step_master.android.R
import com.tusxapps.step_master.android.ui.theme.largeDp

@Composable
fun WaterIconButton(
    @DrawableRes icon: Int,
    modifier: Modifier = Modifier.size(36.dp),
    shape: Shape = CircleShape,
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    Button(
        modifier = modifier,
        shape = shape,
        contentPadding = PaddingValues(largeDp),
        onClick = onClick,
        enabled = enabled
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun WaterIconButtonPreview() {
    WaterIconButton(icon = R.drawable.ic_walking, onClick = {})
}