package com.loki.peti.ui.homeDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.loki.peti.ui.PetiAppViewModel
import com.loki.peti.ui.navigation.HomeScreens

class HomeDetailViewModel: PetiAppViewModel() {

    private val _tabIndex: MutableLiveData<Int> = MutableLiveData(0)
    val tabIndex: LiveData<Int> = _tabIndex

    val tabs = listOf("Today", "Yesterday", "1 Week Ago", "1 Month Ago", "6 Months Ago", "1 Year Ago")


    fun updateTabIndexBasedOnSwipe(isSwipeToLeft: Boolean) {

        _tabIndex.value = when(isSwipeToLeft) {
            true -> Math.floorMod(_tabIndex.value!!.plus(1), tabs.size)
            false -> Math.floorMod(_tabIndex.value!!.minus(1), tabs.size)
        }
    }

    fun updateTabIndex(i: Int) {
        _tabIndex.value = i
    }

    fun navigate(homeDetailTitle: String): String {

        return when(homeDetailTitle) {
            "Vaccine" ->  HomeScreens.AddVaccineScreen.route
            "Activities" -> HomeScreens.AddActivityScreen.route
            "Food" -> HomeScreens.AddFoodScreen.route
            "Hygiene and Care" -> HomeScreens.AddHygieneScreen.route
            "Expense" -> HomeScreens.AddExpenseScreen.route
            else -> HomeScreens.AddNewCategoryScreen.route
        }
    }
}