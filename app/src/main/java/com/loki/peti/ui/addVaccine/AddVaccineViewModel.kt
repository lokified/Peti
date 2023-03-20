package com.loki.peti.ui.addVaccine

import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState
import com.dsc.form_builder.Validators
import com.loki.peti.ui.PetiAppViewModel

class AddVaccineViewModel: PetiAppViewModel() {

    val addVaccineState = FormState(
        fields = listOf(
            TextFieldState(
                name = "vaccineType",
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