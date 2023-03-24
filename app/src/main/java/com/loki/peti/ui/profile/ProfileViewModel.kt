package com.loki.peti.ui.profile

import androidx.compose.runtime.mutableStateOf
import com.loki.peti.data.local.DataStorePreferenceStorage
import com.loki.peti.domain.models.Pet
import com.loki.peti.ui.PetiAppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val datastore: DataStorePreferenceStorage
): PetiAppViewModel() {

    val petProfiles = listOf("Loki", "Brisc")
    val petProfileDetail = mutableStateOf(Pet("", "", "", "", emptyList()))

    init {
        getProfiles()
    }

    private fun getProfiles() {
        launchCatching {
            datastore.getPet().collect {
                petProfileDetail.value = it
            }
        }
    }
}