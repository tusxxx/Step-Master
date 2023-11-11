package com.tusxapps.step_master.data.repositories

import com.tusxapps.step_master.data.network.API
import com.tusxapps.step_master.domain.region.RegionRepository

class RegionRepositoryImpl(
    private val api: API
) : RegionRepository {
    override suspend fun getRegions(): Result<List<String>> = runCatching {
        val regions = api.getRegions().result
        return Result.success(regions.map { it.fullName })
    }
}