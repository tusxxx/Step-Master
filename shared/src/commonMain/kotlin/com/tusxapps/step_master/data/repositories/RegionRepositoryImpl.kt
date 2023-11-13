package com.tusxapps.step_master.data.repositories

import com.tusxapps.step_master.data.network.API
import com.tusxapps.step_master.domain.region.RegionRepository
import com.tusxapps.step_master.utils.suspendRunCatching

class RegionRepositoryImpl(
    private val api: API
) : RegionRepository {
    override suspend fun getRegions(): Result<List<String>> = suspendRunCatching {
        val regions = api.getRegions().result
        regions.map { it.fullName }
    }
}