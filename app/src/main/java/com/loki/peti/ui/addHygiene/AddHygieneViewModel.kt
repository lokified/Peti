package com.loki.peti.ui.addHygiene

import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState
import com.dsc.form_builder.Validators
import com.loki.peti.ui.PetiAppViewModel

class AddHygieneViewModel: PetiAppViewModel() {

    val addHygieneState = FormState(
        fields = listOf(
            TextFieldState(
                name = "itemName",
                validators = listOf(
                    Validators.Required()
                )
            ),
            TextFieldState(
                name = "time",
                validators = listOf(
                    Validators.Required()
                )
            ),
            TextFieldState(
                name = "date",
                validators = listOf(
                    Validators.Required()
                )
            )
        )
    )
}