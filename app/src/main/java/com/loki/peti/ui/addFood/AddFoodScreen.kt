package com.loki.peti.ui.addFood

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
import com.loki.peti.ui.common.*
import java.util.*

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddFoodScreen(
    topBarTitle: String,
    popUp: () -> Unit,
    viewModel: AddFoodViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val formState = remember { viewModel.addFoodState }

    //form values
    val foodType = formState.getState<TextFieldState>("foodType")
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
                    placeholder = "Enter type of Food",
                    value = foodType.value,
                    onValueChange = { foodType.change(it) },
                    errorMessage = foodType.errorMessage,
                    isError = foodType.hasError
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