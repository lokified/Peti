package com.loki.peti.ui.addExpense

import androidx.compose.runtime.Composable
import com.loki.peti.ui.common.TopBar

@Composable
fun AddExpenseScreen(
    topBarTitle: String,
    popUp: () -> Unit
) {
    TopBar(title = "Add $topBarTitle") {

    }
}