package com.example.calculatormad

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable

@Composable
fun CreateButtons(){
    val arrayOfButtons = arrayOf(
        arrayOf("AC","±","%","/",),
        arrayOf("7","8","9","×",),
        arrayOf("4","5","6","-",),
        arrayOf("1","2","3","+"),
        arrayOf("0",",","=")
    )
    for (i in 0..4) {
        Row() {
            for (j in 0..2) {
                if (!(i == 4 && j == 2)){
                    val symbol = arrayOfButtons[i][j]
                    CalculatorButton(buttonName = symbol,
                        groupOfButtons = 1)
                }
            }
            val symbol = arrayOfButtons[i][if(i != 4) 3 else 2]
            CalculatorButton(
                buttonName = symbol,
                groupOfButtons = 2
            )
        }

    }
}