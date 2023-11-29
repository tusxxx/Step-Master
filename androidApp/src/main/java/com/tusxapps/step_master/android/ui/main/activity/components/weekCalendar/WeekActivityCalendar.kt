package com.tusxapps.step_master.android.ui.main.activity.components.weekCalendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.tusxapps.step_master.android.R
import com.tusxapps.step_master.android.ui.theme.MyApplicationTheme
import com.tusxapps.step_master.android.ui.theme.extraLargeDp
import com.tusxapps.step_master.domain.calendar.DayInfo

@Composable
fun WeekActivityCalendar(
    days: List<DayInfo>,
    currentDayInfo: DayInfo?
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(extraLargeDp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        itemsIndexed(days) { index, day ->
            WeekActivityCalendarItem(
                weekDay = when (index) {
                    0 -> stringResource(R.string.weekday_monday)
                    1 -> stringResource(R.string.weekday_tuesday)
                    2 -> stringResource(R.string.weekday_wednesday)
                    3 -> stringResource(R.string.weekday_thursday)
                    4 -> stringResource(R.string.weekday_friday)
                    5 -> stringResource(R.string.weekday_saturday)
                    6 -> stringResource(R.string.weekday_sunday)
                    else -> ""
                },
                date = "${day.localDate.dayOfMonth}/${day.localDate.monthNumber}",
                steps = day.steps,
                activeTime = day.activeTime,
                calories = day.calories,
                goalSteps = day.goalSteps,
                goalActiveTime = day.goalActiveTime,
                goalCalories = day.goalCalories,
                isCurrentDay = day == currentDayInfo
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