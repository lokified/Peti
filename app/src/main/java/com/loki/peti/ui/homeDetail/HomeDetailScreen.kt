package com.loki.peti.ui.homeDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.loki.peti.ui.common.TopBar
import com.loki.peti.ui.tabs.*

@Composable
fun HomeDetailScreen(
    topBarTitle: String,
    openScreen: (String) -> Unit,
    viewModel: HomeDetailViewModel = hiltViewModel()
) {

    TopBar(
        title = topBarTitle,
        floatingButton = {

            IconButton(
                onClick = { openScreen(viewModel.navigate(topBarTitle)) },
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colors.primary),
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    ) {

        TabLayout(viewModel = viewModel, homeDetailTitle = topBarTitle)
    }
}

@Composable
fun TabLayout(viewModel: HomeDetailViewModel, homeDetailTitle: String) {

    val tabIndex = viewModel.tabIndex.observeAsState()
    
    Column(modifier = Modifier.fillMaxWidth()) {
        ScrollableTabRow(
            selectedTabIndex = tabIndex.value!!,
            backgroundColor = Color.White,
            contentColor = Color.Black,
            modifier = Modifier.height(50.dp)
        ) {

            viewModel.tabs.forEachIndexed { index, title ->
                val selected = tabIndex.value!! == index
                Tab(
                    selected = selected,
                    onClick = {
                        viewModel.updateTabIndex(index)
                    },
                    selectedContentColor = MaterialTheme.colors.surface,
                    unselectedContentColor = Color.Black,
                    modifier = Modifier.padding(vertical = 12.dp)
                ) {
                    Text(text = title, fontSize = 16.sp, fontWeight = if(selected) FontWeight.Bold else FontWeight.Normal)
                }
            }
        }

        when(tabIndex.value) {
            0 -> TodayScreen(homeDetailTitle = homeDetailTitle)
            1 -> YesterdayScreen(homeDetailTitle = homeDetailTitle)
            2 -> WeekScreen(homeDetailTitle = homeDetailTitle)
            3 -> OneMonthScreen(homeDetailTitle = homeDetailTitle)
            4 -> SixMonthScreen(homeDetailTitle = homeDetailTitle)
            5 -> YearScreen(homeDetailTitle = homeDetailTitle)
        }
    }
}