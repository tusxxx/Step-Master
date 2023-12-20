package com.tusxapps.step_master.android.ui.main.regionSelection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.androidx.AndroidScreen
import com.tusxapps.step_master.android.ui.components.ExtraLargeSpacer
import com.tusxapps.step_master.android.ui.components.MediumSpacer
import com.tusxapps.step_master.android.ui.components.PrimaryTopBar
import com.tusxapps.step_master.android.ui.components.SearchTextField
import com.tusxapps.step_master.android.ui.main.regionSelection.components.RegionItem
import com.tusxapps.step_master.android.ui.theme.MyApplicationTheme
import com.tusxapps.step_master.android.ui.theme.extraLargeDp

object RegionSelectionScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        RegionSelectionScreenBody()
    }
}

@Composable
fun RegionSelectionScreenBody() {
    val regions = listOf("Москва", "Кемерово", "Новосибирск")
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
            hasBackButton = true
        )
        ExtraLargeSpacer()
        SearchTextField(value = "", onValueChange = {})
        ExtraLargeSpacer()
        LazyColumn {
            items(regions) {
                RegionItem(value = it, onClick = {})
                MediumSpacer()
            }
        }
    }
}

@Preview
@Composable
fun RegionSelectionScreenPreview() {
    MyApplicationTheme {
        Surface {
            RegionSelectionScreenBody()
        }
    }
}