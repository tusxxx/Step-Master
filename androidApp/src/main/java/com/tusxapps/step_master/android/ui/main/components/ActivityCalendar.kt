package com.tusxapps.step_master.android.ui.main.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kizitonwose.calendar.compose.VerticalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.OutDateStyle
import com.tusxapps.step_master.android.ui.components.CircularActivityBar
import com.tusxapps.step_master.android.ui.components.LargeSpacer
import com.tusxapps.step_master.android.ui.components.SmallSpacer
import com.tusxapps.step_master.android.ui.theme.extraLargeDp
import com.tusxapps.step_master.android.ui.theme.mediumDp
import java.time.DayOfWeek
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun ActivityCalendar() {
//    val groupedByMonth = remember(days) {
//        days.groupBy { it.month }
//    }
    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth.minusMonths(12) }
    val endMonth = remember { currentMonth.plusMonths(12) }
    val state = rememberCalendarState(
        startMonth, endMonth, outDateStyle = OutDateStyle.EndOfRow
    )

    Column(Modifier.padding(extraLargeDp)) {
        DaysOfWeekTitle()
        LargeSpacer()
        VerticalCalendar(
            state = state,
            dayContent = {
                Day(day = it)
            },
            monthHeader = {
                Text(
                    text = it.yearMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault()),
                    modifier = Modifier.padding(
                        start = mediumDp,
                        top = extraLargeDp,
                        bottom = extraLargeDp
                    )
                )
            }
        )
    }
}

@Composable
fun DaysOfWeekTitle() {
    val daysOfWeek = remember {
        DayOfWeek.values().toList()
    }
    Row(modifier = Modifier.fillMaxWidth()) {
        for (dayOfWeek in daysOfWeek) {
            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
            )
        }
    }
}

@Composable
fun Day(day: CalendarDay, dayInfo: DayInfo? = null) {
    Box(
        modifier = Modifier, // This is important for square sizing!
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            LargeSpacer()
            CircularActivityBar(
                steps = dayInfo?.steps ?: 10,
                maxSteps = dayInfo?.neededSteps ?: 15,
                activeTime = dayInfo?.activeTime ?: 149,
                maxActiveTime = dayInfo?.neededCalories ?: 180,
                calories = dayInfo?.calories ?: 30,
                maxCalories = dayInfo?.neededCalories ?: 120,
                modifier = Modifier.size(40.dp)
            )
            SmallSpacer()
            Text(
                text = day.date.dayOfMonth.toString(),
                color = if (day.position == DayPosition.MonthDate) LocalTextStyle.current.color else Color.Gray,
                fontSize = 16.sp
            )
        }
    }
}


data class DayInfo(
    val number: Int,
    val steps: Int,
    val neededSteps: Int,
    val calories: Int,
    val neededCalories: Int,
    val activeTime: Int,
    val month: Month,
    val weekDay: WeekDay
)

enum class Month {
    JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC
}

enum class WeekDay {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ActivityCalendarPreview() {
//    ActivityCalendar(emptyList())
}