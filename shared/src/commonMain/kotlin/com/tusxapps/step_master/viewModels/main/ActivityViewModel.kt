package com.tusxapps.step_master.viewModels.main

import com.tusxapps.step_master.domain.calendar.DayInfo
import com.tusxapps.step_master.domain.calendar.Month
import com.tusxapps.step_master.domain.calendar.WeekDay
import com.tusxapps.step_master.viewModels.base.BaseViewModel

class ActivityViewModel : BaseViewModel<ActivityViewModel.State>(State()) {


    data class State(
        val days: List<DayInfo> = listOf(
            DayInfo(
                number = 20,
                steps = 8000,
                goalSteps = 12000,
                activeTime = 135,
                goalActiveTime = 180,
                calories = 170,
                goalCalories = 200,
                weekDay = WeekDay.MONDAY,
                month = Month.NOV
            ),
            DayInfo(
                number = 21,
                steps = 8000,
                goalSteps = 12000,
                activeTime = 135,
                goalActiveTime = 180,
                calories = 170,
                goalCalories = 200,
                weekDay = WeekDay.THURSDAY,
                month = Month.NOV
            ),
            DayInfo(
                number = 22,
                steps = 8000,
                goalSteps = 12000,
                activeTime = 135,
                goalActiveTime = 180,
                calories = 170,
                goalCalories = 200,
                weekDay = WeekDay.WEDNESDAY,
                month = Month.NOV
            ),
            DayInfo(
                number = 23,
                steps = null,
                goalSteps = 12000,
                activeTime = null,
                goalActiveTime = 180,
                calories = null,
                goalCalories = 200,
                weekDay = WeekDay.TUESDAY,
                month = Month.NOV
            ),
            DayInfo(
                number = 24,
                steps = null,
                goalSteps = 12000,
                activeTime = null,
                goalActiveTime = 180,
                calories = null,
                goalCalories = 200,
                weekDay = WeekDay.FRIDAY,
                month = Month.NOV
            ),
            DayInfo(
                number = 25,
                steps = null,
                goalSteps = 12000,
                activeTime = null,
                goalActiveTime = 180,
                calories = null,
                goalCalories = 200,
                weekDay = WeekDay.SATURDAY,
                month = Month.NOV
            ),
            DayInfo(
                number = 26,
                steps = null,
                goalSteps = 12000,
                activeTime = null,
                goalActiveTime = 180,
                calories = null,
                goalCalories = 200,
                weekDay = WeekDay.SUNDAY,
                month = Month.NOV
            )
        ),
        val currentDayIndex: Int = 1
    )
}