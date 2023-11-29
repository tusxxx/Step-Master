package com.tusxapps.step_master.android.ui.main.activity.components.activity

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.androidx.AndroidScreen
import com.tusxapps.step_master.android.ui.main.components.ActivityCalendar
import com.tusxapps.step_master.viewModels.main.ActivityViewModel
import org.koin.androidx.compose.koinViewModel

object ActivityCalendarScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<ActivityViewModel>()
        val state by viewModel.state.collectAsState()

        ActivityCalendar(state.existingDays)
    }
}