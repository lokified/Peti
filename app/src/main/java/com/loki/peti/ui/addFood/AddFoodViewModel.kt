package com.loki.peti.ui.addFood

import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState
import com.dsc.form_builder.Validators
import com.loki.peti.ui.PetiAppViewModel

class AddFoodViewModel: PetiAppViewModel() {

    val addFoodState = FormState(
        fields = listOf(
            TextFieldState(
                name = "foodType",
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
            ),
        )
    )
}