package com.tusxapps.step_master.domain.region

interface RegionRepository {
    suspend fun getRegions(): Result<List<String>>
    suspend fun getRegionIdByName(userRegion: String): Result<String>
    suspend fun getRegionNameById(regionId: String): Result<String>
}