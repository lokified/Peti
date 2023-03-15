package com.loki.peti.data.local

import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import com.loki.peti.domain.models.Pet
import kotlinx.coroutines.flow.Flow

interface PreferenceStorage {

    suspend fun savePet(pet: Pet)

    suspend fun updateFeedingList(feedList: List<String>)

    fun getPet(): Flow<Pet>

    object PreferenceKey{
        val PET_NAME_KEY = stringPreferencesKey("pet_name_key")
        val PET_BIRTH_KEY = stringPreferencesKey("pet_birth_key")
        val PET_GENDER_KEY = stringPreferencesKey("pet_gender_key")
        val PET_IMAGE_KEY = stringPreferencesKey("pet_image_key")
        val PET_FEEDTIME_KEY = stringSetPreferencesKey("pet_feedtime_key")
    }
}