package com.example.calculatormad

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.calculatormad.ui.theme.*


@Composable
fun CreateTextCalculator() {
    Box(
        contentAlignment = Alignment.TopStart,
        modifier = Modifier
            .padding(
                start = LabelTextPadding,
                end = LabelTextPadding,
                top = LabelTextPadding
            )
            .fillMaxHeight(LabelRatio)
            .fillMaxWidth(),
    ) {
        Text(
            text = MainLabel,
            fontSize = MainLabelSize,
            fontFamily = googleSansMedium,
            color = TextColor
        )
    }
}

@Composable
fun CreateWorkspaceForCharterOutput(expression: MutableState<String>) {
    Box(
        contentAlignment = Alignment.TopStart,
        modifier = Modifier
            .padding(
                start = WorkspacePadding,
                end = WorkspacePadding
            )
            .fillMaxWidth()
            .fillMaxHeight(WorkspaceOutputRatio),
    ) {
        Text(
            text = expression.value,
            color = if (expression.value != TextError) CalculateZone else CalculatorZoneError,
            fontSize = OutputExpressionSize
        )
    }
}

@Composable
fun CreateWorkspaceForEnteringCharacters(expression: MutableState<String>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(
                start = WorkspacePadding,
                end = WorkspacePadding
            ),
    ) {
        Box(
            contentAlignment = Alignment.CenterEnd,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(WorkspaceInputRatio)
        ) {
            Text(
                text = expression.value,
                color = if (expression.value != TextError) CalculateZone else CalculatorZoneError,
                fontSize = InputExpressionSize
            )
        }
        Box(
            contentAlignment = Alignment.CenterEnd,
            modifier = Modifier
                .fillMaxSize()
        ) {
            IconButton(
                onClick = { buttonDeleteLastSymbol(expression) }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.vector),
                    contentDescription = IconButtonDescription,
                    tint = Color.White
                )
            }
        }

    }
}