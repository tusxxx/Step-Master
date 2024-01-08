package com.tusxapps.step_master.domain.days

import com.tusxapps.step_master.domain.days.models.DayInfo

interface DayRepository {
    suspend fun getDays(): Result<List<DayInfo>>
    suspend fun getDaysForWeek(): Result<List<DayInfo>>
    suspend fun getToday(): Result<DayInfo>
}
