package com.tusxapps.step_master.utils

import kotlin.math.roundToInt

private const val STEPS_IN_KILOMETER = 1400
private const val MINUTES_IN_HOUR = 60
private const val CALORIES_PER_KILOGRAM_IN_HOUR = 4.5

fun countFatPercent(fatKgs: Float, weightKgs: Float) =
    (fatKgs / weightKgs * 1000).roundToInt() / 10.toFloat()

fun countCalories(activeTime: Float, weightKgs: Float) =
    weightKgs * activeTime * CALORIES_PER_KILOGRAM_IN_HOUR

fun stepsCountToKilometers(stepsCount: Int) =
    (stepsCount.toFloat() / STEPS_IN_KILOMETER * 10).roundToInt() / 10.toFloat()

fun minutesCountToHours(minutesCount: Int) =
    (minutesCount.toFloat() / MINUTES_IN_HOUR * 10).roundToInt() / 10.toFloat()