package com.loki.peti.ui.addExpense

import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState
import com.dsc.form_builder.Validators
import com.loki.peti.ui.PetiAppViewModel

class AddExpenseViewModel : PetiAppViewModel() {

    val addExpenseState = FormState(
        fields = listOf(
            TextFieldState(
                name = "itemName",
                validators = listOf(
                    Validators.Required()
                )
            ),
            TextFieldState(
                name = "amount",
                validators = listOf(
                    Validators.Required()
                )
            ),
            TextFieldState(
                name = "date",
                validators = listOf(
                    Validators.Required()
                )
            ),
        )
    )
}