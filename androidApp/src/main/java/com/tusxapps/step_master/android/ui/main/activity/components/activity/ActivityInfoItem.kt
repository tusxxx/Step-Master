package com.tusxapps.step_master.android.ui.main.activity.components.activity

import androidx.compose.foundation.layout.Arrangement
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
import com.tusxapps.step_master.android.ui.theme.mediumDp

@Composable
fun ActivityInfoItem(
    title: String,
    mainValue: String,
    goalValue: String,
    icon: Int
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(mediumDp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge,
            color = Color.LightGray
        )
        Row {
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
        }
        Text(
            text = "/$goalValue",
            style = MaterialTheme.typography.labelSmall,
            color = Color.Black
        )
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFF)
private fun ActivityInfoIconPreview() {
    ActivityInfoItem(
        title = "Шаги",
        icon = R.drawable.ic_walking,
        mainValue = "8000",
        goalValue = "10000"
    )
}