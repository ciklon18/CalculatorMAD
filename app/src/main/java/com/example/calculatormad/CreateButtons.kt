package com.example.calculatormad

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun CreateButtons(){
    val arrayOfButtons = arrayOf(
        arrayOf(EnumAction.AbsolutelyClear,EnumAction.ChangeSign,EnumAction.Remainder,EnumAction.Divide,),
        arrayOf(EnumAction.NumberSeven,EnumAction.NumberEight,EnumAction.NumberNine,EnumAction.Multiply,),
        arrayOf(EnumAction.NumberFour,EnumAction.NumberFive,EnumAction.NumberSix,EnumAction.Substract),
        arrayOf(EnumAction.NumberOne,EnumAction.NumberTwo,EnumAction.NumberThree,EnumAction.Add),
        arrayOf(EnumAction.NumberZero,EnumAction.Double,EnumAction.Answer)
    )
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()

    ) {
        for (i in 0..4) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()

            ) {
                for (j in 0..2) {
                    if (!(i == 4 && j == 2)){
                        val symbol : EnumAction = arrayOfButtons[i][j]
                        val weightOfButton = if (symbol.string == "0") 2f else 1f
                        ButtonOfCalculator(
                            enumButton = symbol,
                            idGroupOfButtons = 1,
                            onClick = {calculatorAction(string = symbol)},
                            Modifier
                                .weight(weightOfButton),


                        )
                    }
                }
                val symbol = arrayOfButtons[i][if(i != 4) 3 else 2]
                ButtonOfCalculator(
                    enumButton = symbol,
                    idGroupOfButtons = 2,
                    onClick = {calculatorAction(string = symbol)},
                    Modifier
                        .weight(1f)
                )
            }

        }
    }

}
