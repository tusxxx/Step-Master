package com.tusxapps.step_master.domain.calendar

import kotlinx.datetime.LocalDate

data class DayInfo(
    val steps: Int,
    val goalSteps: Int,
    val activeTime: Int,
    val goalActiveTime: Int,
    val calories: Int,
    val goalCalories: Int,
    val localDate: LocalDate
)