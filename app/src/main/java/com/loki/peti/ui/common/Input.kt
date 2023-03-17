package com.loki.peti.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun Input(
    modifier: Modifier = Modifier,
    placeholder: String,
    label: String = "",
    value: String,
    onValueChange: (String) -> Unit,
    errorMessage: String,
    isError: Boolean,
    keyboardType: KeyboardType = KeyboardType.Text
) {


    var passwordVisible by remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        isError = isError,
        label = {
            Text(text = label, modifier = Modifier.padding(bottom = 8.dp))
        },
        placeholder = {
            Text(text = placeholder)
        },
        modifier = modifier
            .fillMaxWidth(),
        colors = textFieldColors(),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        visualTransformation = if (!passwordVisible && keyboardType == KeyboardType.Password) PasswordVisualTransformation()
        else VisualTransformation.None,
        trailingIcon = {

            if(keyboardType == KeyboardType.Password) {

                val image = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = null)
                }
            }
        }
    )

    if (isError) {
        Text(
            text = errorMessage,
            color = MaterialTheme.colors.error,
            fontSize = 12.sp
        )
    }

}


@Composable
fun BasicInput(
    modifier: Modifier = Modifier,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    errorMessage: String = "",
    isError: Boolean = false,
    isIconVisible: Boolean = false,
    trailingIcon: ImageVector? = null,
    onIconClicked: () -> Unit = {}
) {

    Column {
        TextField(
            value = value,
            onValueChange = {
                onValueChange(it)
            },
            isError = isError,
            placeholder = {
                Text(text = placeholder, textAlign = TextAlign.Center)
            },
            modifier = modifier
                .fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                placeholderColor = Color.LightGray
            ),
            trailingIcon = {
                if (isIconVisible) {
                    IconButton(
                        onClick = {
                            onIconClicked()
                        }
                    ) {
                        Icon(
                            imageVector = trailingIcon!!,
                            contentDescription = null,
                            tint = Color.Gray
                        )
                    }
                }
            },
        )

        if (isError) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colors.error,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun textFieldColors(): TextFieldColors {
    return TextFieldDefaults.textFieldColors(
        backgroundColor = Color.Transparent,
        focusedLabelColor = MaterialTheme.colors.primary,
        focusedIndicatorColor = MaterialTheme.colors.primary,
        unfocusedLabelColor = MaterialTheme.colors.onBackground,
        unfocusedIndicatorColor = MaterialTheme.colors.onBackground,
        placeholderColor = Color.Gray
    )
}