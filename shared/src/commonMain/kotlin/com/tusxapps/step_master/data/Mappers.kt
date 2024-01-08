package com.tusxapps.step_master.data

import com.tusxapps.step_master.data.network.models.AchievablesResponse
import com.tusxapps.step_master.data.network.models.ProfileResponse
import com.tusxapps.step_master.domain.Gender
import com.tusxapps.step_master.domain.achievements.models.Achievable
import com.tusxapps.step_master.domain.achievements.models.Achievement
import com.tusxapps.step_master.domain.achievements.models.Rank
import com.tusxapps.step_master.domain.profile.models.Profile

fun ProfileResponse.toDomain(
    regionName: String,
    achievables: List<Achievable> = emptyList(),
    pinnedAchievables: List<Achievable> = emptyList()
) = Profile(
    fullName = fullname,
    nickname = nickname,
    email = email,
    gender = if (gender == "male") Gender.MALE else Gender.FEMALE,
    regionName = regionName,
    placeInRegion = rating?.placeInRegion?.split("/")?.first()?.toInt() ?: 0,
    placeInCountry = rating?.placeInCountry?.split("/")?.first()?.toInt() ?: 0,
    totalPlacesInCountry = rating?.placeInCountry?.split("/")?.last()?.toInt() ?: 0,
    totalPlacesInRegion = rating?.placeInRegion?.split("/")?.last()?.toInt() ?: 0,
    achievables = achievables,
    pinnedAchievables = pinnedAchievables
)

fun AchievablesResponse.Achievement.Data.toDomain(type: String) = Achievement(
    name = name,
    type = type,
    isAchieved = false,
    link = link,
    id = id.toString(),
    groupId = groupId.toString()
)

fun AchievablesResponse.Grade.Data.toDomain(type: String) = Rank(
    name = name,
    type = type,
    isAchieved = false,
    link = link,
    id = id.toString(),
    groupId = groupId.toString()
)