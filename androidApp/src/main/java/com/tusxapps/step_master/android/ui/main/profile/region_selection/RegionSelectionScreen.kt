package com.tusxapps.step_master.android.ui.main.profile.region_selection

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.tusxapps.step_master.android.ui.components.ExtraLargeSpacer
import com.tusxapps.step_master.android.ui.components.LCEView
import com.tusxapps.step_master.android.ui.components.MediumSpacer
import com.tusxapps.step_master.android.ui.components.PrimaryTopBar
import com.tusxapps.step_master.android.ui.components.SearchTextField
import com.tusxapps.step_master.android.ui.main.profile.region_selection.components.RegionItem
import com.tusxapps.step_master.android.ui.theme.MyApplicationTheme
import com.tusxapps.step_master.android.ui.theme.extraLargeDp
import com.tusxapps.step_master.android.ui.theme.mediumDp
import com.tusxapps.step_master.viewModels.main.RegionSelectionViewModel
import org.koin.androidx.compose.koinViewModel

object RegionSelectionScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<RegionSelectionViewModel>()
        val state by viewModel.state.collectAsState()
        val navigator = LocalNavigator.currentOrThrow
        val lce by viewModel.lce.collectAsState()

        LCEView(lce = lce) {
            RegionSelectionScreenBody(
                state = state,
                onRegionClick = {
                    viewModel.onRegionClick(it)
                },
                onSearchTextChange = remember { { viewModel.onSearchTextChange(it) } },
                onBackClick = { navigator.pop() })
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RegionSelectionScreenBody(
    state: RegionSelectionViewModel.State,
    onRegionClick: (String) -> Unit,
    onSearchTextChange: (String) -> Unit,
    onBackClick: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = extraLargeDp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ExtraLargeSpacer()
        PrimaryTopBar(
            text = "Регион",
            hasBackButton = true,
            onBackClick = onBackClick
        )
        ExtraLargeSpacer()
        SearchTextField(value = state.searchText, onValueChange = onSearchTextChange)
        ExtraLargeSpacer()
        LazyColumn(verticalArrangement = Arrangement.spacedBy(mediumDp)) {
            items(state.filteredRegions) {
                RegionItem(
                    value = it,
                    isSelected = state.selectedRegion == it,
                    onClick = { onRegionClick(it) }
                )
            }
            item {
                MediumSpacer()
            }
        }
    }
}

@Preview
@Composable
private fun RegionSelectionScreenPreview() {
    val state = RegionSelectionViewModel.State()
    MyApplicationTheme {
        Surface {
            RegionSelectionScreenBody(state, {}, {}, {})
        }
    }
}