package com.example.calculatormad

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.calculatormad.data_processing.EnumAction
import com.example.calculatormad.ui.theme.*


@Composable
fun ButtonOfCalculator(
    enumButton : EnumAction,
    idGroupOfButtons : Int,
    onClick: (EnumAction) -> Unit,
    modifier: Modifier,
    ){
    val buttonBackgroundColor = if (idGroupOfButtons == 1)
        ButtonPurpleBlue else ButtonRedLilac

    val fontColor = if (idGroupOfButtons == 1)
        TextOfPurpleBlueButtons else TextOfRedLilacButtons

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .padding(ButtonBoxPadding)
            .clip(shape = BoxShape)
            .clickable { onClick.invoke(enumButton) }
            .background(buttonBackgroundColor)
            .fillMaxSize(),
    ) {
        Text(
            text = enumButton.string,
            fontSize = ButtonTextSize,
            color = fontColor,
        )
    }
}

