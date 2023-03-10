package com.example.calculatormad

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.calculatormad.data_processing.EnumAction
import com.example.calculatormad.ui.theme.ButtonRatio
import com.example.calculatormad.ui.theme.RowRatio
import com.example.calculatormad.ui.theme.ZeroButtonRatio
import com.example.calculatormad.ui.theme.ZeroSymbol

@Composable
fun CreateButtons(expression: MutableState<String>,answer: MutableState<String>){
    val arrayOfButtons = arrayOf(
        arrayOf(EnumAction.AllClean, EnumAction.ChangeSign, EnumAction.Remainder, EnumAction.Divide,),
        arrayOf(EnumAction.NumberSeven, EnumAction.NumberEight, EnumAction.NumberNine, EnumAction.Multiply,),
        arrayOf(EnumAction.NumberFour, EnumAction.NumberFive, EnumAction.NumberSix, EnumAction.Subtract),
        arrayOf(EnumAction.NumberOne, EnumAction.NumberTwo, EnumAction.NumberThree, EnumAction.Add),
        arrayOf(EnumAction.NumberZero, EnumAction.Double, EnumAction.Answer)
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
                    .weight(RowRatio)
                    .fillMaxSize()

            ) {
                for (j in 0..2) {
                    if (!(i == 4 && j == 2)){
                        val symbol : EnumAction = arrayOfButtons[i][j]
                        val weightOfButton = if (symbol.string == ZeroSymbol) ZeroButtonRatio else ButtonRatio

                        ButtonOfCalculator(
                            enumButton = symbol,
                            idGroupOfButtons = 1,
                            onClick = {calculatorAction(expression, symbol, answer)},
                            Modifier
                                .weight(weightOfButton),
                        )
                    }
                }
                val symbol = arrayOfButtons[i][if(i != 4) 3 else 2]

                ButtonOfCalculator(
                    enumButton = symbol,
                    idGroupOfButtons = 0,
                    onClick = {calculatorAction(expression, symbol, answer)},
                    Modifier
                        .weight(ButtonRatio)
                )
            }

        }
    }

}
