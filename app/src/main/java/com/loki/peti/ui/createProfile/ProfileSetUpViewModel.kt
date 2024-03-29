package com.loki.peti.ui.createProfile

import android.graphics.Bitmap
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.dsc.form_builder.FormState
import com.dsc.form_builder.TextFieldState
import com.dsc.form_builder.Validators
import com.loki.peti.data.local.DataStorePreferenceStorage
import com.loki.peti.domain.models.Pet
import com.loki.peti.ui.PetiAppViewModel
import com.loki.peti.ui.navigation.OnBoardingScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileSetUpViewModel @Inject constructor(
    private val datastore: DataStorePreferenceStorage
): PetiAppViewModel() {

    var imageBitmap = mutableStateOf<Bitmap?>(null)

    val feedingList = mutableStateListOf<String>()

    val feedTime = mutableStateOf("")

    val feedTimeFrequency = mutableStateOf(0)

    val petSummary = mutableStateOf<Pet>(Pet("", "", "", "", emptyList()))

    init {
        getPetProfileSummary()
    }

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

            val pet = Pet(
                name = petName,
                birthday = birthday,
                gender = gender,
                image = imageBitmap.toString(),
                feedingTime = feedingList
            )
            datastore.savePet(pet)
            openScreen(OnBoardingScreens.ProfileSetup3Screen.route)
        }
    }

    fun isFeedFrequencyValid() = feedTimeFrequency.value != 0

    fun isFeedListEqualFrequency() = feedingList.size <= feedTimeFrequency.value - 1

    fun addFeedTime(time: String) {

        feedingList.add(time)

        feedTime.value = ""

        updateFeedTimeList()
    }

    private fun updateFeedTimeList() {
        launchCatching {
            datastore.updateFeedingList(feedingList)
        }
    }

    fun deleteFeedTime(time: String) {
        feedTimeFrequency.value--
        feedingList.remove(time)
    }

    fun deleteRecentFeedTime() {
        if (feedingList.isNotEmpty()) {
            feedingList.remove(feedingList[feedingList.size - 1])
        }
    }

    private fun getPetProfileSummary() {
        launchCatching {
            datastore.getPet().collect { pet ->
                petSummary.value = pet
            }
        }
    }
}