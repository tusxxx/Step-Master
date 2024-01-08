package com.tusxapps.step_master.domain.profile.models

data class AchievementProgressData(
    val name: String,
    val prevIcon: String,
    val nextIcon: String,
    val dealtKm: Int,
    val neededKm: Int
)
