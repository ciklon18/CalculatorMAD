package com.example.calculatormad

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
fun ButtonOfCalculator(
    buttonName : EnumAction,
    groupOfButtons : Int,
    onClick: (EnumAction) -> Unit,
    modifer: Modifier,

){
    val ButtonBackgroundColor = if (groupOfButtons == 1)
        ButtonPurpleBlue else ButtonRedLilac

    val FontColor = if (groupOfButtons == 1)
        TextOfPurpleBlueButtons else TextOfRedLilacButtons

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifer
            .padding(8.dp)
            .clip(shape = RoundedCornerShape(28.dp))
            .clickable { onClick.invoke(buttonName) }
            .background(ButtonBackgroundColor)
            .fillMaxHeight(),
    ) {
        Text(
            text = buttonName.toString(),
            fontSize = 32.sp,
            color = FontColor,
        )
    }
}

