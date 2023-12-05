package com.tusxapps.step_master.android.ui.main.summary.components.body

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.tusxapps.step_master.android.R
import com.tusxapps.step_master.android.ui.components.SmallSpacer

@Composable
fun BodyInfoItem(
    value: Float? = null,
    measurement: String,
    icon: Int
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color.Unspecified
        )
        SmallSpacer()
        Row(verticalAlignment = Alignment.Bottom) {
            Text(
                text = value.toString().takeIf { value != null } ?: "_ _",
                style = MaterialTheme.typography.titleMedium
            )
            SmallSpacer()
            Text(
                text = measurement,
                style = MaterialTheme.typography.labelSmall,
                color = Color.LightGray
            )
        }
    }
}

@Composable
@Preview
fun BodyInfoItemPreview() {
    BodyInfoItem(
        value = 50.0f,
        measurement = "кг",
        icon = R.drawable.ic_weight
    )
}