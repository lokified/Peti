package com.loki.peti.presentation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopBar(
    title: String,
    content: @Composable () -> Unit
) {

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier.fillMaxWidth()
                    .height(50.dp),
                contentAlignment = Alignment.Center
            )  {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }
    ){ padding ->
            content()
    }
}