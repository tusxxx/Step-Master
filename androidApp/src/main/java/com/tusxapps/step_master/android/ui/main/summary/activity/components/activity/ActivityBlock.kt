package com.tusxapps.step_master.android.ui.main.summary.activity.components.activity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.tusxapps.step_master.android.ui.theme.MyApplicationTheme
import com.tusxapps.step_master.android.ui.theme.extraLargeDp
import com.tusxapps.step_master.android.ui.theme.mediumDp
import com.tusxapps.step_master.android.ui.theme.shadowColor
import com.tusxapps.step_master.utils.stepsCountToKilometers

@Composable
fun ActivityBlock(
    steps: Int,
    goalSteps: Int,
    activeTime: Int,
    goalActiveTime: Int,
    calories: Int,
    goalCalories: Int
) {
    Column(
        modifier = Modifier
            .shadow(
                elevation = 4.dp,
                spotColor = shadowColor,
                shape = RoundedCornerShape(mediumDp)
            )
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(mediumDp))
            .background(Color.White, shape = RoundedCornerShape(mediumDp))
            .padding(extraLargeDp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularActivityBar(
            steps = steps,
            maxSteps = goalSteps,
            activeTime = activeTime,
            maxActiveTime = goalActiveTime,
            calories = calories,
            maxCalories = goalCalories,
            modifier = Modifier.size(152.dp)
        )
        ExtraLargeSpacer()
        ExtraLargeSpacer()
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            ActivityInfoItem(
                title = "Шаги",
                mainValue = "$steps",
                goalValue = "$goalSteps",
                icon = R.drawable.ic_walking
            )
            ActivityInfoItem(
                title = "Активное время",
                mainValue = "${activeTime}м.",
                goalValue = "$goalActiveTime мин.",
                icon = R.drawable.ic_clock_circle
            )
            ActivityInfoItem(
                title = "Сожжено",
                mainValue = "$calories",
                goalValue = "$goalCalories ккал",
                icon = R.drawable.ic_calories
            )
        }
        ExtraLargeSpacer()
        TextWithDots(
            startText = "Расстояние: ",
            endText =
            if (steps != 0) {
                "~${stepsCountToKilometers(steps)} км"
            } else {
                "0 км"
            }
        )
    }
}

@Preview
@Composable
fun ActivityBlockPreview() {
    MyApplicationTheme {
        ActivityBlock(
            steps = 8000,
            goalSteps = 12000,
            activeTime = 158,
            goalActiveTime = 120,
            calories = 137,
            goalCalories = 250
        )
    }
}