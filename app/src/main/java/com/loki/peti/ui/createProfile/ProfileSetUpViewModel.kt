package com.loki.peti.ui.createProfile

import android.graphics.Bitmap
import androidx.compose.runtime.mutableStateOf
import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState
import com.dsc.form_builder.Validators
import com.loki.peti.ui.PetiAppViewModel
import com.loki.peti.ui.navigation.OnBoardingScreens

class ProfileSetUpViewModel: PetiAppViewModel() {

    var imageBitmap = mutableStateOf<Bitmap?>(null)

    val feedingList = mutableListOf<String>("8:00 AM", "5:00 PM")

    val feedTime = mutableStateOf("")

    val petProfileState = FormState(
        fields = listOf(
            TextFieldState(
                name = "petName",
                validators = listOf(
                    Validators.Required()
                )
            ),
            TextFieldState(
                name = "birthdate",
                validators = listOf(
                    Validators.Required()
                )
            ),
            TextFieldState(
                name = "gender",
                validators = listOf(
                    Validators.Required()
                )
            )
        )
    )

    fun savePetProfile(
        petName: String,
        birthday: String,
        gender: String,
        openScreen: (String) -> Unit
    ) {

        launchCatching {

            openScreen(OnBoardingScreens.ProfileSetup3Screen.route)
        }
    }

    fun addFeedTime(time: String) {

        feedingList.add(time)

        feedTime.value = ""
    }

    fun deleteFeedTime(time: String) {
        feedingList.remove(time)
    }
}