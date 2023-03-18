package com.loki.peti.ui.addActivities

import androidx.compose.runtime.Composable
import com.loki.peti.ui.common.TopBar

@Composable
fun AddActivitiesScreen(
    topBarTitle: String,
    popUp: () -> Unit
) {
    TopBar(title = "Add $topBarTitle") {

    }
}