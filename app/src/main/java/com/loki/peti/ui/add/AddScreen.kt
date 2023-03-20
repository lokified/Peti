package com.loki.peti.ui.add

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dsc.form_builder.TextFieldState
import com.loki.peti.ui.common.ButtonSection
import com.loki.peti.ui.common.TopBar
import com.loki.peti.ui.common.TransparentInput

@Composable
fun AddScreen(
    popUp: () -> Unit,
    viewModel: AddCategoryViewModel = hiltViewModel()
) {

    val formState = remember { viewModel.addState }
    val title = formState.getState<TextFieldState>("title")


    TopBar(title = "Add Category") {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 24.dp, horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {

            Column {

                TransparentInput(
                    placeholder = "Enter Category Name",
                    value = title.value,
                    onValueChange = { title.change(it) },
                    errorMessage = title.errorMessage,
                    isError = title.hasError
                )

                Spacer(modifier = Modifier.weight(1f))

                ButtonSection(text = "Save") {
                    popUp()
                }
            }
        }
    }
}