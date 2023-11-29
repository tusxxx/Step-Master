package com.tusxapps.step_master.data.repositories

import com.tusxapps.step_master.data.network.API
import com.tusxapps.step_master.data.network.models.DaysResponse
import com.tusxapps.step_master.data.prefs.PreferencesStorage
import com.tusxapps.step_master.domain.calendar.DayInfo
import com.tusxapps.step_master.domain.days.DayRepository
import com.tusxapps.step_master.utils.suspendRunCatching
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDate
import kotlinx.datetime.todayIn

class DayRepositoryImpl(
    private val api: API,
    private val preferencesStorage: PreferencesStorage
) : DayRepository {
    override suspend fun getDays(): Result<List<DayInfo>> = suspendRunCatching {
        api.getDays().result.map {
            it.toDayInfo()
        }
    }

    override suspend fun getDaysForWeek(): Result<List<DayInfo>> = suspendRunCatching {
        api.getDays().result
            .takeLast(7)
            .map {
                DayInfo(
                    steps = it.steps,
                    goalSteps = it.plansteps,
                    activeTime = it.distance.toInt(),
                    goalActiveTime = it.plandistance.toInt(),
                    calories = it.calories.toInt(),
                    goalCalories = it.plancalories.toInt(),
                    localDate = it.date.toLocalDateOfAPIPattern()
                )
            }
            .dropLast(1)
            .plus(getToday().getOrThrow())
    }

    override suspend fun getToday(): Result<DayInfo> = suspendRunCatching {
        val today = DayInfo(
            steps = preferencesStorage.todaySteps,
            goalSteps = preferencesStorage.goalSteps,
            activeTime = preferencesStorage.goalActiveTime,
            goalActiveTime = preferencesStorage.goalActiveTime,
            calories = preferencesStorage.todayCalories,
            goalCalories = preferencesStorage.goalCalories,
            localDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
        )

        today
    }
}

fun DaysResponse.Result.toDayInfo(): DayInfo =
    DayInfo(
        steps = steps,
        goalSteps = plansteps,
        activeTime = distance.toInt(),
        goalActiveTime = plandistance.toInt(),
        calories = calories.toInt(),
        goalCalories = plancalories.toInt(),
        localDate = date.toLocalDateOfAPIPattern()
    )

fun String.toLocalDateOfAPIPattern(): LocalDate {
    val dayMonthYear = this.split('/')
    return (dayMonthYear[2] + "-" + dayMonthYear[1] + "-" + dayMonthYear[0]).toLocalDate()
}
