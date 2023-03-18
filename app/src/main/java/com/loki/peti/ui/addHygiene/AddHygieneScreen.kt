package com.loki.peti.ui.addHygiene

import androidx.compose.runtime.Composable
import com.loki.peti.ui.common.TopBar

@Composable
fun AddHygieneScreen(
    topBarTitle: String,
    popUp: () -> Unit
) {
    TopBar(title = "Add $topBarTitle") {

    }
}