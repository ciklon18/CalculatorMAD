package com.example.calculatormad

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculatormad.ui.theme.ButtonPurpleBlue
import com.example.calculatormad.ui.theme.ButtonRedLilac
import com.example.calculatormad.ui.theme.TextOfPurpleBlueButtons
import com.example.calculatormad.ui.theme.TextOfRedLilacButtons


@Composable
fun CalculatorButton(buttonName : String, groupOfButtons : Int){
    val ButtonBackgroundColor = if (groupOfButtons == 1)  ButtonPurpleBlue else ButtonRedLilac
    val FontColor = if (groupOfButtons == 1)  TextOfPurpleBlueButtons else TextOfRedLilacButtons
    var ButtonWigth = ButtonWigth(name = buttonName);

    Box(
        modifier = Modifier
            .clickable { }
            .padding(8.dp)
            .clip(shape = RoundedCornerShape(28.dp))
            .height(83.dp)
            .width(ButtonWigth)
            .background(ButtonBackgroundColor),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = buttonName,
            fontSize = 32.sp,
            color = FontColor,
        )
    }
}

@Composable
fun ButtonWigth(name : String) = when(name){
    "0" -> 182.dp
    else -> 83.dp
}