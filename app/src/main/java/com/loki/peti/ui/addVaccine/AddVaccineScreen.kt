package com.loki.peti.ui.addVaccine

import androidx.compose.runtime.Composable
import com.loki.peti.ui.common.TopBar

@Composable
fun AddVaccineScreen(
    topBarTitle: String,
    popUp: () -> Unit
) {
    TopBar(title = "Add $topBarTitle") {

    }
}