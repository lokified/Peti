package com.loki.peti.ui.addExpense

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
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
fun AddExpenseScreen(
    topBarTitle: String,
    popUp: () -> Unit,
    viewModel: AddExpenseViewModel = hiltViewModel(),
    detailTabsViewModel: HomeDetailTabsViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val formState = remember { viewModel.addExpenseState }

    //form values
    val itemName = formState.getState<TextFieldState>("itemName")
    val amount = formState.getState<TextFieldState>("amount")
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
                    placeholder = "Enter item name",
                    value = itemName.value,
                    onValueChange = { itemName.change(it) },
                    errorMessage = itemName.errorMessage,
                    isError = itemName.hasError
                )


                TransparentInput(
                    modifier = Modifier.padding(vertical = 8.dp),
                    placeholder = "Enter amount",
                    value = amount.value,
                    onValueChange = { amount.change(it) },
                    errorMessage = amount.errorMessage,
                    isError = amount.hasError,
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
                        detailTabsViewModel.addExpense(
                            HomeDetail(
                                titleDescription = itemName.value,
                                expenseAmount = amount.value.toDouble(),
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