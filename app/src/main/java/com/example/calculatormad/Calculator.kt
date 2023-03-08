package com.example.calculatormad

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculatormad.ui.theme.CalculateZone
import com.example.calculatormad.ui.theme.DividerColor
import com.example.calculatormad.ui.theme.TextColor
import com.example.calculatormad.ui.theme.googleSansMedium
import kotlin.math.exp

@Composable
fun Calculator() {
    val expression = remember {
        mutableStateOf("")
    }
    Box(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {
        Column(Modifier.fillMaxSize()) {
            CreateTextCalculator()
            Box(
                modifier = Modifier
                    .fillMaxHeight(0.32f)
                    .fillMaxWidth()
            ){
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxHeight(0.24f)
                            .fillMaxWidth()
                    )
                    CreateWorkspaceForCharterOutput(expression)
                    CreateWorkspaceForEnteringCharacters(expression)
                }

            }
            Divider(
                color = DividerColor,
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                CreateButtons(expression = expression)
            }

        }

    }
}

@Composable
fun CreateTextCalculator(){
    Box(
        contentAlignment = Alignment.TopStart,
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .fillMaxHeight(0.05f)
            .fillMaxWidth(),
        ) {
        Text(
            text = "Calculator",
            fontSize = 28.sp,
            fontFamily = googleSansMedium,
            color = TextColor
        )
    }
}

@Composable
fun CreateWorkspaceForCharterOutput(expression : MutableState<String>){
    Box(
        contentAlignment = Alignment.TopStart,
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .fillMaxHeight(0.52f),
    ){
        Text(
            text = expression.value,
            color = CalculateZone,
            fontSize = 40.sp
        )
    }
}
@Composable
fun CreateWorkspaceForEnteringCharacters(expression : MutableState<String>){
    Row(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
    ){
        Box(
            contentAlignment = Alignment.CenterEnd,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.835f)
        ){
            Text(
                text = expression.value,
                color = CalculateZone,
                fontSize =40.sp
            )
        }
        Box(
            contentAlignment = Alignment.CenterEnd,
            modifier = Modifier
                .fillMaxSize()
        ){
            IconButton(onClick = {buttonDeleteLastSymbol(expression) },) {
                Icon(
                    painter = painterResource(id = R.drawable.vector),
                    contentDescription = "Delete the last element",
                    tint = Color.White
                )
            }
        }

    }
}