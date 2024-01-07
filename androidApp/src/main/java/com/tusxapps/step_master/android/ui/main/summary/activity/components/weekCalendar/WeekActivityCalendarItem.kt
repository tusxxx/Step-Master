package com.tusxapps.step_master.android.ui.main.summary.activity.components.weekCalendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tusxapps.step_master.android.ui.components.CircularActivityBar
import com.tusxapps.step_master.android.ui.components.ExtraLargeSpacer
import com.tusxapps.step_master.android.ui.components.LargeSpacer
import com.tusxapps.step_master.android.ui.components.MediumSpacer
import com.tusxapps.step_master.android.ui.components.SmallSpacer
import com.tusxapps.step_master.android.ui.theme.MyApplicationTheme
import com.tusxapps.step_master.android.ui.theme.smallDp

@Composable
fun WeekActivityCalendarItem(
    weekDay: String,
    date: String,
    steps: Int? = null,
    goalSteps: Int,
    activeTime: Int? = null,
    goalActiveTime: Int,
    calories: Int? = null,
    goalCalories: Int,
    isCurrentDay: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = weekDay,
            style = MaterialTheme.typography.labelLarge,
            color = Color.Black
        )
        LargeSpacer()
        Column(
            modifier = Modifier
                .background(
                    color = if (isCurrentDay) {
                        Color(0xFFE7E5F2)
                    } else {
                        Color.Transparent
                    },
                    shape = RoundedCornerShape(100.dp)
                )
                .clip(shape = RoundedCornerShape(100.dp))
                .padding(smallDp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
            } else {
                Spacer(modifier = Modifier.size(36.dp))
            }
            ExtraLargeSpacer()
            Text(
                text = date,
                style = MaterialTheme.typography.labelMedium,
                color = Color.Black
            )
            MediumSpacer()
        }
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
                goalCalories = 250,
                isCurrentDay = true
            )
        }
    }
}