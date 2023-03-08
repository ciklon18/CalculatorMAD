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
            .padding(8.dp)
            .clip(shape = RoundedCornerShape(28.dp))
            .clickable { onClick.invoke(enumButton) }
            .background(buttonBackgroundColor)
            .fillMaxSize(),
    ) {
        Text(
            text = enumButton.string,
            fontSize = 32.sp,
            color = fontColor,
        )
    }
}

