package com.loki.peti.ui.common

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TopBar(
    title: String,
    bottomBar: @Composable () -> Unit = {},
    floatingButton: @Composable () -> Unit = {},
    trailingText: String = "",
    onTrailingTextClick: () -> Unit = {},
    content: @Composable () -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.background,
                elevation = 1.dp
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Spacer(modifier = Modifier.weight(0.5f))
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )

                    Spacer(modifier = Modifier.weight(0.5f))

                    Text(
                        text = trailingText,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .clickable { onTrailingTextClick() }
                    )
                }
            }
        },
        bottomBar = {
            bottomBar()
        },
        floatingActionButton = {
            floatingButton()
        }
    ){
            content()
    }
}