package com.loki.peti.ui.add

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dsc.form_builder.TextFieldState
import com.loki.peti.ui.common.BasicInput
import com.loki.peti.ui.common.ButtonSection
import com.loki.peti.ui.common.TopBar
import com.loki.peti.ui.theme.Teal_100

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

                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp)
                            .background(color = Teal_100, shape = RoundedCornerShape(5.dp))
                    ) {


                        BasicInput(
                            placeholder = "Enter Category Name",
                            value = title.value,
                            onValueChange = { title.change(it) },
                            errorMessage = title.errorMessage,
                            isError = title.hasError,
                            isIconVisible = false
                        )
                    }

                    if (title.hasError) {
                        Text(
                            text = title.errorMessage,
                            color = MaterialTheme.colors.error,
                            fontSize = 12.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                ButtonSection(text = "Save") {
                    popUp()
                }
            }
        }
    }
}