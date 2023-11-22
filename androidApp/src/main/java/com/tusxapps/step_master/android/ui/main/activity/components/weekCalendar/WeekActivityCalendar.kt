package com.tusxapps.step_master.android.ui.main.activity.components.weekCalendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tusxapps.step_master.android.ui.theme.MyApplicationTheme
import com.tusxapps.step_master.android.ui.theme.extraLargeDp
import com.tusxapps.step_master.domain.calendar.DayInfo

@Composable
fun WeekActivityCalendar(
    days: List<DayInfo>,
    currentDayIndex: Int
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(extraLargeDp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(days) {
            WeekActivityCalendarItem(
                weekDay = it.weekDay.weekDayName,
                date = "${it.number}/${it.month.ordinal + 1}",
                steps = it.steps,
                activeTime = it.activeTime,
                calories = it.calories,
                goalSteps = it.goalSteps,
                goalActiveTime = it.goalActiveTime,
                goalCalories = it.goalCalories,
                isCurrentDay = it.number == days[currentDayIndex].number
            )
        }
    }
}

@Preview
@Composable
fun WeekActivityCalendarPreview() {
    MyApplicationTheme {
        Surface {
//            WeekActivityCalendar(
//            )
        }
    }
}