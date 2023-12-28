package com.tusxapps.step_master.domain.profile

import com.tusxapps.step_master.domain.Gender

data class Profile(
    val fullName: String,
    val nickname: String,
    val email: String,
    val gender: Gender,
    val regionName: String,
    val placeInRegion: Int,
    val placeInCountry: Int,
    val totalPlacesInCountry: Int,
    val totalPlacesInRegion: Int
)
