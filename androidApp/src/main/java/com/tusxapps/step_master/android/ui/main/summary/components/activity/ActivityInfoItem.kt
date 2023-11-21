package com.tusxapps.step_master.android.ui.main.summary.components.activity

import androidx.compose.foundation.layout.Column
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
fun ActivityInfoItem(
    mainValue: String,
    alternativeValue: String,
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
        Text(
            text = mainValue,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = alternativeValue,
            style = MaterialTheme.typography.labelSmall,
            color = Color.LightGray
        )
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFF)
private fun ActivityInfoItemPreview() {
    ActivityInfoItem(icon = R.drawable.ic_walking, mainValue = "8000", alternativeValue = "~3,6 км")
}