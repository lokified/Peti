package com.loki.peti.ui.addActivities

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Timer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dsc.form_builder.TextFieldState
import com.loki.peti.domain.models.HomeDetail
import com.loki.peti.ui.common.*
import com.loki.peti.ui.tabs.HomeDetailTabsViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddActivitiesScreen(
    topBarTitle: String,
    popUp: () -> Unit,
    viewModel: AddActivitiesViewModel = hiltViewModel(),
    detailTabsViewModel: HomeDetailTabsViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val formState = remember { viewModel.addActivityState }

    //form values
    val activityType = formState.getState<TextFieldState>("activityType")
    val startTime = formState.getState<TextFieldState>("startTime")
    val endTime = formState.getState<TextFieldState>("endTime")
    val date = formState.getState<TextFieldState>("date")


    TopBar(title = "Add $topBarTitle") {

        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {

                TransparentInput(
                    modifier = Modifier.padding(vertical = 8.dp),
                    placeholder = "Enter Type of Activity",
                    value = activityType.value,
                    onValueChange = { activityType.change(it) },
                    errorMessage = activityType.errorMessage,
                    isError = activityType.hasError
                )

                val startTimePicker = timePickerLayout(context, { startTime.change(it) })

                TransparentInput(
                    modifier = Modifier.padding(vertical = 8.dp),
                    placeholder = "Select Start Time",
                    value = startTime.value,
                    onValueChange = { startTime.change(it) },
                    errorMessage = startTime.errorMessage,
                    isError = startTime.hasError,
                    isIconVisible = true,
                    trailingIcon = Icons.Rounded.Timer,
                    onIconClicked = {
                        startTimePicker.show()
                        keyboardController?.hide()
                    }
                )

                val endTimePicker = timePickerLayout(context, { endTime.change(it) })

                TransparentInput(
                    modifier = Modifier.padding(vertical = 8.dp),
                    placeholder = "Select End Time",
                    value = endTime.value,
                    onValueChange = { endTime.change(it) },
                    errorMessage = endTime.errorMessage,
                    isError = endTime.hasError,
                    isIconVisible = true,
                    trailingIcon = Icons.Rounded.Timer,
                    onIconClicked = {
                        endTimePicker.show()
                        keyboardController?.hide()
                    }
                )

                val datePicker = datePickerLayout(context, { date.change(it) })

                TransparentInput(
                    modifier = Modifier.padding(vertical = 8.dp),
                    placeholder = "Select Date",
                    value = date.value,
                    onValueChange = { date.change(it) },
                    errorMessage = date.errorMessage,
                    isError = date.hasError,
                    isIconVisible = true,
                    trailingIcon = Icons.Rounded.DateRange,
                    onIconClicked = {
                        datePicker.show()
                        keyboardController?.hide()
                    }
                )

                Spacer(modifier = Modifier.weight(1f))

                ButtonSection(
                    text = "Save",
                    modifier = Modifier.padding(vertical = 16.dp)
                ) {
                    if (formState.validate()) {
                        detailTabsViewModel.addActivities(
                            HomeDetail(
                                titleDescription = activityType.value,
                                startTime = startTime.value,
                                endTime = endTime.value,
                                dateRecord = date.value
                            )
                        )
                        popUp()
                        keyboardController?.hide()
                    }
                }
            }
        }
    }
}