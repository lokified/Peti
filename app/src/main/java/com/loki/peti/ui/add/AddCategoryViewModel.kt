package com.loki.peti.ui.add

import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState
import com.dsc.form_builder.Validators
import com.loki.peti.ui.PetiAppViewModel
import com.loki.peti.ui.navigation.HomeScreens

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

    fun saveCategory(title: String, openAndPopUp: (String, String) -> Unit) {

        launchCatching {

            openAndPopUp(HomeScreens.HomeScreen.route, HomeScreens.AddNewCategoryScreen.route)
        }
    }
}