package com.tusxapps.step_master.android.ui.main.profile.components.rating

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color

@Composable
fun RatingStatsText(
    name: String,
    currentValue: Int,
    goalValue: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$name: ",
            style = MaterialTheme.typography.labelSmall,
            color = Color.Black
        )
        Text(
            text = "$currentValue",
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black
        )
        Text(
            text = "/$goalValue",
            style = MaterialTheme.typography.labelSmall,
            color = Color.LightGray
        )
    }
}