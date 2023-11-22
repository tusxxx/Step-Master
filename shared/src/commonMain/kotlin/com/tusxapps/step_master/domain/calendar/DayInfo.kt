package com.tusxapps.step_master.domain.calendar

data class DayInfo(
    val number: Int,
    val steps: Int?,
    val goalSteps: Int,
    val activeTime: Int?,
    val goalActiveTime: Int,
    val calories: Int?,
    val goalCalories: Int,
    val month: Month,
    val weekDay: WeekDay
)