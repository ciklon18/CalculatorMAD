package com.example.calculatormad

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculatormad.ui.theme.CalculateZone
import com.example.calculatormad.ui.theme.DividerColor
import com.example.calculatormad.ui.theme.TextColor
import com.example.calculatormad.ui.theme.googleSansMedium

@Composable
fun Calculator() {

    Box(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {
        Column(Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .height(36.dp)
                    .width(380.dp),
                contentAlignment = Alignment.TopStart,

                ) {
                Text(text = "Calculator", fontSize = 28.sp, fontFamily = googleSansMedium,
                color = TextColor)
            }
            Spacer(modifier = Modifier
                .height(60.dp)
                .fillMaxWidth())
            Box(modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 89.dp)
                .fillMaxWidth()
                .height(100.dp),
                contentAlignment = Alignment.TopStart,
            ){
                Text(text = "2345,003", color = CalculateZone, fontSize = 57.sp)
            }
            Divider(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp), color = DividerColor)

            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier.background(Color.Black)
                ) {
                    CreateButtons()
                }

            }

        }

    }
}