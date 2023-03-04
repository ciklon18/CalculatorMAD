package com.example.calculatormad

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun CreateButtons(){
    val arrayOfButtons = arrayOf(
        arrayOf("AC","±","%","/",),
        arrayOf("7","8","9","×",),
        arrayOf("4","5","6","-",),
        arrayOf("1","2","3","+"),
        arrayOf("0",",","=")
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
                        val symbol = arrayOfButtons[i][j]
                        val weightOfButton = if (symbol == "0") 2f else 1f
                        ButtonOfCalculator(
                            buttonName = symbol,
                            groupOfButtons = 1,
                            Modifier
                                .weight(weightOfButton)

                        )
                    }
                }
                val symbol = arrayOfButtons[i][if(i != 4) 3 else 2]
                ButtonOfCalculator(
                    buttonName = symbol,
                    groupOfButtons = 2,
                    Modifier
                        .weight(1f)
                )
            }

        }
    }

}
