package com.loki.peti.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.loki.peti.data.local.PreferenceStorage.PreferenceKey.PET_BIRTH_KEY
import com.loki.peti.data.local.PreferenceStorage.PreferenceKey.PET_FEEDTIME_KEY
import com.loki.peti.data.local.PreferenceStorage.PreferenceKey.PET_GENDER_KEY
import com.loki.peti.data.local.PreferenceStorage.PreferenceKey.PET_IMAGE_KEY
import com.loki.peti.data.local.PreferenceStorage.PreferenceKey.PET_NAME_KEY
import com.loki.peti.domain.models.Pet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStorePreferenceStorage @Inject constructor(
    private val dataStore: DataStore<Preferences>
): PreferenceStorage {

    override suspend fun savePet(pet: Pet) {

        dataStore.edit { preference ->
            preference[PET_NAME_KEY] = pet.name
            preference[PET_BIRTH_KEY] = pet.birthday
            preference[PET_GENDER_KEY] = pet.gender
            preference[PET_IMAGE_KEY] = pet.image
            preference[PET_FEEDTIME_KEY] = pet.feedingTime.toSet()
        }
    }

    override suspend fun updateFeedingList(feedList: List<String>) {
        dataStore.edit { preference ->
            preference[PET_FEEDTIME_KEY] = feedList.toSet()
        }
    }

    override fun getPet(): Flow<Pet> {

        return dataStore.data.map { preferences ->
            val name = preferences[PET_NAME_KEY] ?: ""
            val birthday = preferences[PET_BIRTH_KEY] ?: ""
            val gender = preferences[PET_GENDER_KEY] ?: ""
            val image = preferences[PET_IMAGE_KEY] ?: ""
            val feedTime = preferences[PET_FEEDTIME_KEY] ?: emptySet()

            Pet(name, birthday, gender, image, feedTime.toList())
        }
    }
}