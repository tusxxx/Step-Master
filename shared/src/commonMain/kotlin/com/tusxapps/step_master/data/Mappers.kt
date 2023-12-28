package com.tusxapps.step_master.data

import com.tusxapps.step_master.data.network.models.ProfileResponse
import com.tusxapps.step_master.domain.Gender
import com.tusxapps.step_master.domain.profile.Profile

fun ProfileResponse.toDomain(regionName: String) = Profile(
    fullName = fullname,
    nickname = nickname,
    email = email,
    gender = if (gender == "male") Gender.MALE else Gender.FEMALE,
    regionName = regionName,
    placeInRegion = rating?.placeInRegion?.split("/")?.first()?.toInt() ?: 0,
    placeInCountry = rating?.placeInCountry?.split("/")?.first()?.toInt() ?: 0,
    totalPlacesInCountry = rating?.placeInCountry?.split("/")?.last()?.toInt() ?: 0,
    totalPlacesInRegion = rating?.placeInRegion?.split("/")?.last()?.toInt() ?: 0
)