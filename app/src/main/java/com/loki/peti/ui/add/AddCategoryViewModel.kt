package com.loki.peti.ui.add

import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState
import com.dsc.form_builder.Validators
import com.loki.peti.ui.PetiAppViewModel

class AddCategoryViewModel: PetiAppViewModel() {

    val addState = FormState(
        fields = listOf(
            TextFieldState(
                name = "title",
                validators = listOf(
                    Validators.Required()
                )
            )
        )
    )
}