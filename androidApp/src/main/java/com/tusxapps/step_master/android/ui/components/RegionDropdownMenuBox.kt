//package com.tusxapps.step_master.android.ui.components
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.heightIn
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.Divider
//import androidx.compose.material3.DropdownMenuItem
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.ExposedDropdownMenuBox
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.window.Dialog
//import com.tusxapps.step_master.android.R
//import com.tusxapps.step_master.viewModels.auth.RegisterViewModel
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun RegionDropDownMenuBox(
////    value: String,
//    state: RegisterViewModel.State,
//    onValueChange: (String) -> Unit,
////    options: List<String>
//) {
//    var expanded by remember { mutableStateOf(false) }
//
//    ExposedDropdownMenuBox(
//        expanded = expanded,
//        onExpandedChange = {
//            expanded = !expanded
//        }
//    ) {
//        PrimaryTextField(
//            modifier = Modifier.menuAnchor(),
//            value = state.region,
//            onValueChange = {
//                expanded = true
//                onValueChange(it)
//            },
//            hint = "Регион",
//            icon = R.drawable.ic_search,
//        )
//        ExposedDropdownMenu(
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(Color.White),
//            expanded = expanded,
//            onDismissRequest = {
//                expanded = false
//            }
//        ) {
//            Dialog(onDismissRequest = { /*TODO*/ }) {
//                LazyColumn(
//                    Modifier
//                        .heightIn(64.dp, 120.dp)
//                        .width(240.dp)) {
//                    items(state.filteredRegions) {
//                        DropdownMenuItem(
//                            text = {
//                                Text(it)
//                            },
//                            onClick = {
//                                onValueChange(it)
//                                expanded = false
//                            }
//                        )
//                        Divider(Modifier.fillMaxWidth())
//
//                    }
//                }
//            }
//        }
//    }
//}