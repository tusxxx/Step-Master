package com.tusxapps.step_master.domain.profile.models

import com.tusxapps.step_master.domain.Gender
import com.tusxapps.step_master.domain.achievements.models.Achievable

data class Profile(
    val fullName: String,
    val nickname: String,
    val email: String,
    val gender: Gender,
    val regionName: String,
    val placeInRegion: Int,
    val placeInCountry: Int,
    val totalPlacesInCountry: Int,
    val totalPlacesInRegion: Int,
    val achievables: List<Achievable>,
    val pinnedAchievables: List<Achievable>
)
