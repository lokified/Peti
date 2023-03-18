package com.loki.peti.ui.addNewSubCategory

import androidx.compose.runtime.Composable
import com.loki.peti.ui.common.TopBar

@Composable
fun AddNewSubCategoryScreen(
    topBarTitle: String,
    popUp: () -> Unit
) {
    TopBar(title = "Add $topBarTitle") {

    }
}