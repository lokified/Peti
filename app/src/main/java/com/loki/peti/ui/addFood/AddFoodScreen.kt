package com.loki.peti.ui.addFood

import androidx.compose.runtime.Composable
import com.loki.peti.ui.common.TopBar

@Composable
fun AddFoodScreen(
    topBarTitle: String,
    popUp: () -> Unit
) {

    TopBar(title = "Add $topBarTitle") {

    }
}