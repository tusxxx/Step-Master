package com.tusxapps.step_master.data.repositories

import com.tusxapps.step_master.data.network.API
import com.tusxapps.step_master.domain.region.RegionRepository
import com.tusxapps.step_master.utils.suspendRunCatching

class RegionRepositoryImpl(
    private val api: API
) : RegionRepository {
    // todo add cache
    // todo add region model
    override suspend fun getRegions(): Result<List<String>> = suspendRunCatching {
        val regions = api.getRegions().result
        regions.map { it.fullName }
    }

    override suspend fun getRegionIdByName(userRegion: String): Result<String> = suspendRunCatching {
        val regions = api.getRegions().result

        regions.firstOrNull {
            it.fullName.trim().lowercase() == userRegion.trim().lowercase()
        }!!.id
    }

    override suspend fun getRegionNameById(regionId: String): Result<String> = suspendRunCatching {
        val regions = api.getRegions().result
        regions.firstOrNull {
            it.id == regionId
        }!!.fullName
    }
}