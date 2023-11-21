package com.tusxapps.step_master.android.ui.main.activity.components.weekCalendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tusxapps.step_master.android.ui.components.CircularActivityBar
import com.tusxapps.step_master.android.ui.components.MediumSpacer
import com.tusxapps.step_master.android.ui.components.SmallSpacer
import com.tusxapps.step_master.android.ui.theme.MyApplicationTheme

@Composable
fun WeekActivityCalendarItem(
    weekDay: String,
    date: String,
    steps: Int? = null,
    goalSteps: Int,
    activeTime: Int? = null,
    goalActiveTime: Int,
    calories: Int? = null,
    goalCalories: Int
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = weekDay,
            style = MaterialTheme.typography.labelLarge,
            color = Color.Black
        )
        SmallSpacer()
        if (
            steps != null &&
            activeTime != null &&
            calories != null
        ) {
            CircularActivityBar(
                steps = steps,
                maxSteps = goalSteps,
                activeTime = activeTime,
                maxActiveTime = goalActiveTime,
                calories = calories,
                maxCalories = goalCalories,
                modifier = Modifier.size(36.dp)
            )
        }
        MediumSpacer()
        Text(
            text = date,
            style = MaterialTheme.typography.labelMedium,
            color = Color.Black
        )
    }
}

@Composable
@Preview
fun WeekActivityCalendarItemPreview() {
    MyApplicationTheme {
        Surface {
            WeekActivityCalendarItem(
                weekDay = "Пн",
                date = "21/11",
                steps = 0,
                goalSteps = 12000,
                activeTime = 0,
                goalActiveTime = 120,
                calories = 0,
                goalCalories = 250
            )
        }
    }
}