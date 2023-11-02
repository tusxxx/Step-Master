//package com.tusxapps.step_master.android.ui.components
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Row
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonColors
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.Immutable
//import androidx.compose.runtime.MutableState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.tooling.preview.Preview
//import com.tusxapps.step_master.android.ui.theme.largeDp
//
//@Composable
//fun ChipSelector(chipSelectorState: ChipSelectorState) {
//    Row {
//        chipSelectorState.selectables.forEach {
//            val color = if (it.isSelected) {
//                MaterialTheme.colorScheme.primary
//            } else {
//                MaterialTheme.colorScheme.secondary
//            }
//            Button(onClick = {}, colors = ButtonDefaults.buttonColors(containerColor = color)) {
//                Text(text = it.text)
//            }
//        }
//    }
//}
//
//@Composable
//fun rememberChipSelectorState(list: List<Selectable>) =
//    remember { mutableStateOf(ChipSelectorState(selectables = list)) }
//
//class ChipSelectorState(
//    selectables: List<SelectableData>
//) {
//    private var selectablesState by mutableStateOf(selectables)
//
//    fun select(selectable: SelectableData) {
//        val selectableIndex = selectablesState.indexOfFirst { it.text == selectable.text }
//        if (selectableIndex != -1) {
//            selectablesState = selectablesState.toMutableList().mapIndexed { index, selectableData ->
//                if (index == selectableIndex) {
//                    selectableData.copy(isSelected = true)
//                } else {
//                    selectableData.copy(isSelected = false)
//                }
//            }
//        }
//    }
//
//    data class SelectableData (
//        val isSelected: Boolean,
//        val text: String
//    )
//}
//
//
//@Composable
//@Preview(showBackground = true)
//fun ChipSelectorPreview(
//    chipSelectorState: MutableState<ChipSelectorState> = rememberChipSelectorState(
//        listOf(A(isSelected = false, text = "Мужчина"), A(isSelected = false, text = "Женщина"))
//    )
//) {
//    val state by chipSelectorState
//
//    Row(horizontalArrangement = Arrangement.spacedBy(largeDp)) {
//        state.selectables.forEach {
//            val color = if (state.selected) {
//                MaterialTheme.colorScheme.primary
//            } else {
//                MaterialTheme.colorScheme.secondary
//            }
//            Button(onClick = {state.}, colors = ButtonDefaults.buttonColors(containerColor = color)) {
//                Text(text = it.text)
//            }
//        }
//    }
//}
//
