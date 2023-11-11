package com.tusxapps.step_master.domain.region

interface RegionRepository {
    suspend fun getRegions(): Result<List<String>>
}