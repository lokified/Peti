package com.loki.peti.ui.addVaccine

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
fun AddVaccineScreen(
    topBarTitle: String,
    popUp: () -> Unit,
    viewModel: AddVaccineViewModel = hiltViewModel(),
    detailTabsViewModel: HomeDetailTabsViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val formState = remember { viewModel.addVaccineState }

    //form values
    val vaccineType = formState.getState<TextFieldState>("vaccineType")
    val time = formState.getState<TextFieldState>("time")
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
                    placeholder = "Enter Vaccine Type",
                    value = vaccineType.value,
                    onValueChange = { vaccineType.change(it) },
                    errorMessage = vaccineType.errorMessage,
                    isError = vaccineType.hasError
                )

                val timePicker = timePickerLayout(context, { time.change(it) })

                TransparentInput(
                    modifier = Modifier.padding(vertical = 8.dp),
                    placeholder = "Select Time",
                    value = time.value,
                    onValueChange = { time.change(it) },
                    errorMessage = time.errorMessage,
                    isError = time.hasError,
                    isIconVisible = true,
                    trailingIcon = Icons.Rounded.Timer,
                    onIconClicked = {
                        timePicker.show()
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
                        detailTabsViewModel.addVaccine(
                            HomeDetail(
                                titleDescription = vaccineType.value,
                                timeRecord = time.value,
                                dateRecord = date.value
                            )
                        )
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
                        popUp()
                        keyboardController?.hide()
                    }
                }
            }
        }
    }
}