package com.tusxapps.step_master.viewModels.main

import com.rickclephas.kmm.viewmodel.coroutineScope
import com.tusxapps.step_master.domain.profile.ProfileRepository
import com.tusxapps.step_master.domain.region.RegionRepository
import com.tusxapps.step_master.viewModels.base.BaseViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegionSelectionViewModel(
    private val regionsRepository: RegionRepository,
    private val profileRepository: ProfileRepository
) : BaseViewModel<RegionSelectionViewModel.State>(State()) {
    init {
        viewModelScope.coroutineScope.launch {
            load {
                regionsRepository.getRegions().onSuccess { regions ->
                    mutableState.update { it.copy(allRegions = regions, filteredRegions = regions) }
                }
            }
        }
    }

    fun onSearchTextChange(text: String) {
        mutableState.update {
            val filteredRegions = it.allRegions.filter { region ->
                text == "" || region.lowercase().contains(text.lowercase())
            }
            it.copy(searchText = text, filteredRegions = filteredRegions)
        }
    }

    fun onRegionClick(region: String) {
        viewModelScope.coroutineScope.launch {
            mutableState.update { it.copy(selectedRegion = region) }
            profileRepository.updateProfile(regionName = region)
        }
    }

    data class State(
        val searchText: String = "",
        val selectedRegion: String = "",
        val allRegions: List<String> = emptyList(),
        val filteredRegions: List<String> = allRegions
    )
}