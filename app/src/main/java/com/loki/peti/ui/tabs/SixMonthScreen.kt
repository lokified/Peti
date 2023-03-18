package com.loki.peti.ui.tabs

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.loki.peti.ui.common.ItemDetail
import com.loki.peti.ui.homeDetail.HomeDetailViewModel

@Composable
fun SixMonthScreen(
    homeDetailTitle: String,
    viewModel: HomeDetailTabsViewModel = hiltViewModel(),
    homeViewModel: HomeDetailViewModel = hiltViewModel()
) {

    var isSwipeToTheLeft by remember { mutableStateOf(false) }
    val dragState = rememberDraggableState(onDelta = { delta ->
        isSwipeToTheLeft = delta > 0
    })
    val data = viewModel.dataToDisplay(homeDetailTitle)

    Box(
        modifier = Modifier.fillMaxSize()
            .draggable(
                state = dragState,
                orientation = Orientation.Horizontal,
                onDragStarted = {},
                onDragStopped = {
                    homeViewModel.updateTabIndexBasedOnSwipe(isSwipeToTheLeft)
                }
            )
    ) {

        LazyColumn {

            items(data) { detail ->

                ItemDetail(homeDetail = detail, modifier = Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ))
            }
        }
    }
}