package com.example.calculatormad

import androidx.compose.runtime.MutableState
import com.example.calculatormad.data_processing.EnumAction
import com.example.calculatormad.ui.theme.TextError


val signs = arrayOf("+", "-", "×", "÷", "%")
var signExist = false
var signOfFirstNumberWasChanged = false
var firstNumberIsDouble = false
var secondNumberIsDouble = false
var firstNumber = "0"
var secondNumber = ""
var currentSign = ""


fun calculatorAction(
    expression: MutableState<String>,
    enumName: EnumAction,
    answer: MutableState<String>
) {
    if (expression.value == TextError) {
        when (enumName) {
            EnumAction.AllClean -> buttonAllClean(expression, answer)
            else -> {

            }
        }
    } else if (expression.value == "-"){
        when (enumName) {
            EnumAction.AllClean -> buttonAllClean(expression, answer)
            EnumAction.DeleteLastSymbol -> buttonDeleteLastSymbol(expression)
            EnumAction.ChangeSign -> buttonChangeSign(expression)
            else -> {
                if (enumName.ordinal in 0..9)
                    buttonAddNumber(expression, enumName)
            }
        }
    } else if (expression.value.length < 26) {
        when (enumName) {
            EnumAction.AllClean -> buttonAllClean(expression, answer)
            EnumAction.DeleteLastSymbol -> buttonDeleteLastSymbol(expression)
            EnumAction.ChangeSign -> buttonChangeSign(expression)
            EnumAction.Remainder -> buttonRemainder(expression)
            EnumAction.Add -> buttonAdd(expression)
            EnumAction.Subtract -> buttonSubtract(expression)
            EnumAction.Multiply -> buttonMultiply(expression)
            EnumAction.Divide -> buttonDivide(expression)
            EnumAction.Answer -> buttonAnswer(expression, answer)
            EnumAction.Double -> buttonDouble(expression)
            else -> {
                if (enumName.ordinal in 0..9)
                    buttonAddNumber(expression, enumName)
            }
        }
    } else {
        when (enumName) {
            EnumAction.AllClean -> buttonAllClean(expression, answer)
            EnumAction.DeleteLastSymbol -> buttonDeleteLastSymbol(expression)
            EnumAction.Answer -> buttonAnswer(expression, answer)
            else -> {

            }
        }
    }

}

fun buttonAddNumber(
    expression: MutableState<String>,
    enumName: EnumAction
) {
    if (expression.value == "-"){
        expression.value += enumName.string
        firstNumber = "-${enumName.string}"
    }
    else if (expression.value == "0") {
        expression.value = enumName.string
        firstNumber = enumName.string
    } else {
        expression.value += enumName.string
        if (signExist) {
            secondNumber =
                if (secondNumber != "0") (secondNumber + enumName.string) else enumName.string
        } else {
            firstNumber += enumName.string
        }
    }
}

fun signChangePattern(currValue: String, sign: String): String {
    var expression = currValue
    if (secondNumber == "" && currValue.isNotEmpty() && currValue[currValue.length - 1]
            .toString() != sign
    ) {
        if (signExist) {
            expression = currValue.substring(0, currValue.length - 1)
        }
        expression += sign
        signExist = true
        currentSign = sign
    }
    return expression
}

fun getResultOfCalculation(): Float {
    if (currentSign == "×") {
        return firstNumber.toFloat() * secondNumber.toFloat()
    } else if (currentSign == "÷") {
        if (secondNumber != "0")
            return firstNumber.toFloat() / secondNumber.toFloat()
    } else if (currentSign == "+") {
        return firstNumber.toFloat() + secondNumber.toFloat()
    } else if (currentSign == "-") {
        return firstNumber.toFloat() - secondNumber.toFloat()
    } else if (currentSign == "%") {
        return if (secondNumber == "")
            (firstNumber.toFloat() * 100 / 10000)
        else
            (firstNumber.toFloat() * secondNumber.toFloat()) / 100
    }
    return 0.0f
}

fun removeDotFromFirstNumberAndChangeFlagIfNecessary() {
    if (firstNumber.isNotEmpty()) {
        if (firstNumber[firstNumber.length - 1] == '.') {
            firstNumber = firstNumber.substring(0, firstNumber.length - 1)
            firstNumberIsDouble = false
        }
    }

}

fun removeDotFromSecondNumberAndChangeFlagIfNecessary() {
    if (secondNumber.isNotEmpty()) {
        if (secondNumber[secondNumber.length - 1] == '.') {
            secondNumber = secondNumber.substring(0, secondNumber.length - 1)
            secondNumberIsDouble = false
        }
    }

}
fun changeOutputCharacterToComma(expression: MutableState<String>, answer: MutableState<String>){
    expression.value = expression.value.replace(".", ",")
    answer.value = answer.value.replace(".", ",")
}
fun buttonAnswer(expression: MutableState<String>, answer: MutableState<String>) {
    if (signs.contains(currentSign)) {
        if ((secondNumber != "") || (currentSign == "%")) {
            removeDotFromSecondNumberAndChangeFlagIfNecessary()

            if (currentSign == "÷" && secondNumber == "0") {
                expression.value = TextError
            } else {
                val result = getResultOfCalculation()
                expression.value = if (result == 0.0f) "0" else result.toString()
                firstNumber = if (result == 0.0f) "0" else result.toString()
            }

            signOfFirstNumberWasChanged = expression.value[0] == '-'
            secondNumber = ""
        } else {
            removeDotFromFirstNumberAndChangeFlagIfNecessary()
            if (firstNumber != "") {
                expression.value = firstNumber
                signOfFirstNumberWasChanged = expression.value[0] == '-'
            } else {
                expression.value = "0"
                firstNumber = ""
                signOfFirstNumberWasChanged = false
            }
        }
        signExist = false
        currentSign = ""
        secondNumberIsDouble = false
    } else {
        removeDotFromFirstNumberAndChangeFlagIfNecessary()
        expression.value = if (firstNumber != "") firstNumber else "0"
        signOfFirstNumberWasChanged = expression.value[0] == '-'
    }

    answer.value = if (expression.value == "Error") expression.value else " = ${expression.value}"
    changeOutputCharacterToComma(expression, answer)
}


fun buttonDivide(expression: MutableState<String>) {
    expression.value = signChangePattern(expression.value, "÷")
}

fun buttonMultiply(expression: MutableState<String>) {
    expression.value = signChangePattern(expression.value, "×")
}


fun buttonSubtract(expression: MutableState<String>) {
    if (expression.value == "0"){
        expression.value = "-"
        firstNumber = "-"
        signOfFirstNumberWasChanged = true
    } else{
        expression.value = signChangePattern(expression.value, "-")
    }
}


fun buttonAdd(expression: MutableState<String>) {
    expression.value = signChangePattern(expression.value, "+")
}


fun buttonRemainder(expression: MutableState<String>) {
    expression.value = signChangePattern(expression.value, "%")
}


fun buttonChangeSign(expression: MutableState<String>) {
    if ((firstNumber != "") && (firstNumber != "0") && !signExist) {
        if (!signOfFirstNumberWasChanged) {
            firstNumber = "-$firstNumber"
            expression.value = firstNumber.replace(".",",")
            signOfFirstNumberWasChanged = true
        } else if (firstNumber != "") {
            firstNumber = firstNumber.substring(1, firstNumber.length)
            expression.value = firstNumber.replace(".",",")
            signOfFirstNumberWasChanged = false
        }
    } else if (expression.value == "-"){
        expression.value = ""
        signOfFirstNumberWasChanged = false
    }
}

fun buttonAllClean(expression: MutableState<String>, answer: MutableState<String>) {
    expression.value = "0"
    answer.value = ""
    signExist = false
    signOfFirstNumberWasChanged = false
    firstNumberIsDouble = false
    secondNumberIsDouble = false
    firstNumber = "0"
    secondNumber = ""
    currentSign = ""
}

fun buttonDouble(expression: MutableState<String>) {
    if (signExist && !secondNumberIsDouble) {
        if (secondNumber == "") {
            expression.value += "0"
            secondNumber += "0"
        }
        expression.value += ","
        secondNumber += "."
        secondNumberIsDouble = true

    } else if (!firstNumberIsDouble) {
        expression.value += ","
        firstNumber += "."
        firstNumberIsDouble = true
    }
}


fun buttonDeleteLastSymbol(expression: MutableState<String>) {
    var currValue = expression.value

    if (currValue != "Error" && currValue.isNotEmpty() && (currValue != "0")) {

        if (!signExist && (currValue.length == 2) && (currValue[0] == '-')) {
            firstNumber = "0"
            signOfFirstNumberWasChanged = false
        } else {
            val isContain = currValue[currValue.length - 1].toString()

            if (signs.contains(isContain)) {
                signExist = false
                currentSign = ""

            } else if (isContain == ",") {
                if (signExist)
                    secondNumberIsDouble = false
                else
                    firstNumberIsDouble = false
            } else if (signExist) {
                secondNumber = secondNumber.substring(0, secondNumber.length - 1)
            } else {
                firstNumber = firstNumber.substring(0, firstNumber.length - 1)
                if (firstNumber.isEmpty()) {
                    firstNumber = "0"
                }
            }
        }

        expression.value =
            if (firstNumber != "0") {
                currValue.substring(0, currValue.length - 1)
            } else "0"
    }

}