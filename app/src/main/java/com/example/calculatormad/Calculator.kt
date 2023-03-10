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
import com.example.calculatormad.ui.theme.*

@Composable
fun Calculator() {
    val answer = remember {
        mutableStateOf("")
    }
    val expression = remember {
        mutableStateOf(ZeroSymbol)
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
                    .fillMaxHeight(WorkspaceRatio)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxHeight(SpacerRatio)
                            .fillMaxWidth()
                    )
                    CreateWorkspaceForCharterOutput(answer)
                    CreateWorkspaceForEnteringCharacters(expression)
                }

            }
            Divider(
                color = DividerColor,
                modifier = Modifier
                    .padding(
                        start = DividerPaddingWidth,
                        end = DividerPaddingWidth,
                        top = DividerPaddingHeight,
                        bottom = DividerPaddingHeight),
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                CreateButtons(expression = expression, answer = answer)
            }

        }

    }
}

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
                color = if (expression.value !=TextError) CalculateZone else CalculatorZoneError,
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