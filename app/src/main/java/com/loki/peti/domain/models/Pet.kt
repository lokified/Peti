package com.loki.peti.domain.models

data class Pet(
    val name: String,
    val birthday: String,
    val gender: String,
    val image: String,
    val feedingTime: List<String>
)
