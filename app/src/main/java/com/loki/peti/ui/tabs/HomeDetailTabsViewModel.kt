package com.loki.peti.ui.tabs

import com.loki.peti.domain.models.HomeDetail
import com.loki.peti.ui.PetiAppViewModel

class HomeDetailTabsViewModel: PetiAppViewModel() {

    private val hygieneDetails = mutableListOf(
        HomeDetail(
            titleDescription = "Washed the cat today",
            timeRecord = "12:00 PM"
        )
    )

    fun addHygiene(hygieneDetail: HomeDetail) {
        hygieneDetails.add(hygieneDetail)
    }

    private val vaccineDetails = mutableListOf(
        HomeDetail(
            titleDescription = "Rabies Vaccine",
            timeRecord = "05:00 PM",
            dateRecord = "21/04/2022"
        ),
        HomeDetail(
            titleDescription = "Calicivirus Vaccine",
            timeRecord = "01:00 PM",
            dateRecord = "01/04/2022"
        )
    )

    fun addVaccine(vaccineDetail: HomeDetail) {
        vaccineDetails.add(vaccineDetail)
    }

    private val foodDetails = mutableListOf(
        HomeDetail(
            titleDescription = "Fish Bone",
            timeRecord = "05:00 PM"
        )
    )

    fun addFood(foodDetail: HomeDetail) {
        foodDetails.add(foodDetail)
    }

    private val activitiesRecord = mutableListOf(
        HomeDetail(
            titleDescription = "Exercises",
            startTime = "06:00 AM",
            endTime = "07:00 AM"
        ),
        HomeDetail(
            titleDescription = "Playtime",
            startTime = "06:00 AM",
            endTime = "07:00 AM"
        ),
        HomeDetail(
            titleDescription = "Training",
            startTime = "06:00 AM",
            endTime = "07:00 AM"
        )
    )

    fun addActivities(activityDetail: HomeDetail) {
        activitiesRecord.add(activityDetail)
    }

    private val expenseRecords = mutableListOf(
        HomeDetail(
            titleDescription = "Bought Fish",
            expenseAmount = 2.00
        )
    )

    fun addExpense(expenseDetail: HomeDetail) {
        expenseRecords.add(expenseDetail)
    }

    fun dataToDisplay(homeDetailTitle: String): List<HomeDetail> {

        return when(homeDetailTitle) {

            "Vaccine" -> vaccineDetails
            "Activities" -> activitiesRecord
            "Food" -> foodDetails
            "Hygiene And Care" -> hygieneDetails
            "Expense" -> expenseRecords
            else -> emptyList()
        }
    }
}