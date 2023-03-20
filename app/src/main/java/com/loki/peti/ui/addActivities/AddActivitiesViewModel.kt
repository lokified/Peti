package com.loki.peti.ui.addActivities

import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState
import com.dsc.form_builder.Validators
import com.loki.peti.ui.PetiAppViewModel

class AddActivitiesViewModel: PetiAppViewModel() {

    val addActivityState = FormState(
        fields = listOf(
            TextFieldState(
                name = "activityType",
                validators = listOf(
                    Validators.Required()
                )
            ),
            TextFieldState(
                name = "startTime",
                validators = listOf(
                    Validators.Required()
                )
            ),
            TextFieldState(
                name = "endTime",
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