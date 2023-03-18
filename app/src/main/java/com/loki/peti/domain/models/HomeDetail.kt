package com.loki.peti.domain.models

data class HomeDetail(
    val titleDescription: String,
    val expenseAmount: Double? = null,
    val timeRecord: String? = null,
    val dateRecord: String? = null,
    val startTime: String? = null,
    val endTime: String? = null
)