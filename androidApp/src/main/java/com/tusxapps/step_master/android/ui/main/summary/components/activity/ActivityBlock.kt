package com.tusxapps.step_master.android.ui.main.summary.components.activity

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tusxapps.step_master.android.R
import com.tusxapps.step_master.android.ui.components.CircularActivityBar
import com.tusxapps.step_master.android.ui.components.ExtraLargeSpacer
import com.tusxapps.step_master.android.ui.theme.extraLargeDp
import com.tusxapps.step_master.android.ui.theme.largeDp
import com.tusxapps.step_master.android.ui.theme.mediumDp
import com.tusxapps.step_master.android.ui.theme.shadowColor
import com.tusxapps.step_master.utils.minutesCountToHours
import com.tusxapps.step_master.utils.stepsCountToKilometers

@Composable
fun ActivityBlock(
    steps: Int,
    activeTime: Int,
    calories: Int,
    goalSteps: Int,
    goalActiveTime: Int,
    goalCalories: Int,
    onBlockClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .shadow(
                elevation = 4.dp,
                spotColor = shadowColor,
                shape = RoundedCornerShape(mediumDp)
            )
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(mediumDp))
            .background(Color.White, shape = RoundedCornerShape(mediumDp))
            .padding(extraLargeDp)
            .clickable { onBlockClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Активность",
                style = MaterialTheme.typography.titleMedium
            )
            ExtraLargeSpacer()
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(largeDp)
            ) {
                ActivityInfoItem(
                    icon = R.drawable.ic_walking,
                    mainValue = "$steps",
                    alternativeValue =
                    if (steps != 0) {
                        "~${stepsCountToKilometers(steps)} км"
                    } else {
                        "0 км"
                    }
                )
                ActivityInfoItem(
                    icon = R.drawable.ic_clock_circle,
                    mainValue = "${activeTime}м.",
                    alternativeValue =
                    if (activeTime != 0) {
                        "~${minutesCountToHours(activeTime)} ч"
                    } else {
                        "0 ч"
                    }
                )
                ActivityInfoItem(
                    icon = R.drawable.ic_calories,
                    mainValue = "$calories",
                    alternativeValue = "ккал"
                )
            }
        }
        CircularActivityBar(
            steps = steps,
            maxSteps = goalSteps,
            activeTime = activeTime,
            maxActiveTime = goalActiveTime,
            calories = calories,
            maxCalories = goalCalories,
            modifier = Modifier.size(100.dp)
        )
    }
}

@Composable
@Preview
private fun ActivityBlockPreview() {
    ActivityBlock(
        steps = 8000,
        activeTime = 158,
        calories = 176,
        goalSteps = 12000,
        goalActiveTime = 120,
        goalCalories = 250,
        onBlockClick = {}
    )
}