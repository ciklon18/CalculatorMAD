package com.example.calculatormad

import androidx.compose.runtime.MutableState

// нужно проверять на double и количество точек не более 1
// число максимум 9 символов, не учитывая точку, +- и просто знак
// нужно проверять на деление на 0
// нужно проверять на то, что введен ли какой-то символ
// нужно сделать проверку на длину. Строка должна быть менее 12 ссимволов, иначе можно использовать только AllClean, DeleteLastSymbol, Answer
// для строки ввода отдельная проверка
// для удаления последнего символа смена изменение double может быть, удаление знака ну и изменение самого числа
// добавить по умолчанию значение, равное нулю
val signs = arrayOf("+","-","*","/")
var signExist = false
var signIsChange = false
var firstNumberIsDouble = false
var secondNumberIsDouble = false
var firstNumber = ""
var secondNumber = ""
var currentSign = ""
fun calculatorAction(expression : MutableState<String>, enumName: EnumAction){
    if (expression.value.length < 17){
        when(enumName){
            EnumAction.AllClean -> buttonAllClean(expression)
            EnumAction.DeleteLastSymbol -> buttonDeleteLastSymbol(expression)
            EnumAction.ChangeSign -> buttonChangeSign(expression)
            EnumAction.Remainder -> buttonRemainder(expression)
            EnumAction.Add -> buttonAdd(expression)
            EnumAction.Subtract -> buttonSubtract(expression)
            EnumAction.Multiply -> buttonMultiply(expression)
            EnumAction.Divide -> buttonDivide(expression)
            EnumAction.Answer -> buttonAnswer(expression)
            EnumAction.Double -> buttonDouble(expression)
            else -> {
                if (enumName.ordinal in 0..9)
                    buttonAddNumber(expression, enumName)
            }
        }
    } else{
        when(enumName){
            EnumAction.AllClean -> buttonAllClean(expression)
            EnumAction.DeleteLastSymbol -> buttonDeleteLastSymbol(expression)
            else ->{
                if (enumName.string == "=")
                    buttonAnswer(expression)
            }
        }
    }

}



fun buttonAddNumber(expression : MutableState<String>, enumName : EnumAction) { // нужно корректно сделать signExist
    if (expression.value == "0"){
        expression.value = enumName.string
        firstNumber = enumName.string
    } else {
        expression.value += enumName.string

        if (signExist){
            if (secondNumber != "0")
                secondNumber += enumName.string
            else
                secondNumber = enumName.string
        } else{
            firstNumber += enumName.string
        }
    }

}


fun buttonAnswer(expression : MutableState<String>) {
    if (signs.contains(currentSign)){
        var result = 0.0
        if (secondNumber != "")
        {
            if (currentSign == "*"){
                result = firstNumber.toDouble() * secondNumber.toDouble()
            } else if (currentSign == "/"){
                result = firstNumber.toDouble() / secondNumber.toDouble()
            }else if (currentSign =="+"){
                result = firstNumber.toDouble() + secondNumber.toDouble()
            }else if (currentSign == "-"){
                result = firstNumber.toDouble() - secondNumber.toDouble()
            }
            expression.value = result.toString()
            firstNumber = result.toString()
            signIsChange = if (expression.value.get(0) == '-') true else false
            secondNumber = ""
        } else{
            if (firstNumber != ""){
                expression.value = firstNumber
                signIsChange = if (expression.value.get(0) == '-') true else false
            } else{
                expression.value = "0.0"
                firstNumber = ""
                signIsChange = false
            }
        }
        firstNumberIsDouble = false // проверить даблы
        signExist = false
        secondNumberIsDouble = false
        currentSign = ""

    } else {
        expression.value = if (firstNumber != "") firstNumber else "0.0"
        signIsChange = if  (expression.value.get(0) == '-') true else false
    }
}


fun buttonDivide(expression : MutableState<String>) {

}

fun buttonMultiply(expression : MutableState<String>) {

}


fun buttonSubtract(expression : MutableState<String>) {

}


fun buttonAdd(expression : MutableState<String>) {
    var currValue = expression.value
    val sign = "+"
    if (isSecondNumberNull() && isExpressionNotEmpty(currValue) && isThisNotRepeatedSign(currValue, sign)){
        if (signExist){
            currValue = currValue.substring(0, currValue.length - 1)
        }
        currValue += sign
        expression.value = currValue

        signExist = true
        currentSign = sign
    }
}


fun buttonRemainder(expression : MutableState<String>) {
    TODO("Not yet implemented")
}


fun buttonChangeSign(expression : MutableState<String>) {
    if (firstNumber != "" && !signExist){
        if (!signIsChange){
            firstNumber = "-" + firstNumber
            expression.value = firstNumber
            signIsChange = true
        } else if (firstNumber != ""){
            firstNumber = firstNumber.substring(1, firstNumber.length)
            expression.value = firstNumber
            signIsChange = false
        }
    }

}


fun isSignOrDoubleSymbol(isContain : String){
    if (signs.contains(isContain)){
        signExist = true
        currentSign = isContain
    }
    else if (isContain == ".")
        if (signExist)
            secondNumberIsDouble = false
        else
            firstNumberIsDouble = false
}
fun checkingTheEnteredCharacters(expression : String){
    var isContain = expression.get(expression.length - 1).toString()

    if (signs.contains(isContain)){
        signExist = false
        currentSign = ""
    } else if (isContain == "."){
        if (signExist)
            secondNumberIsDouble = false
        else
            firstNumberIsDouble = false
    }else if (signExist){
        secondNumber = secondNumber.substring(0, secondNumber.length - 1)
        isSignOrDoubleSymbol(isContain)
    } else{
        firstNumber = firstNumber.substring(0, firstNumber.length - 1)
        isSignOrDoubleSymbol(isContain)
    }
}
fun buttonDeleteLastSymbol(expression : MutableState<String>) {
    if (!expression.value.isEmpty()){
        var currValue = expression.value
        checkingTheEnteredCharacters(currValue)
        expression.value = currValue.substring(0, currValue.length - 1)
    }

}

fun buttonAllClean(expression : MutableState<String>) {
    expression.value = ""
    signExist = false
    signIsChange = false
    firstNumberIsDouble = false
    secondNumberIsDouble = false
    firstNumber = ""
    secondNumber = ""
    currentSign = ""
}

fun buttonDouble(expression: MutableState<String>) {
    expression.value += "."
    if (signExist && !secondNumberIsDouble){
        secondNumber += "."
        secondNumberIsDouble = true
    } else if(!firstNumberIsDouble){
        firstNumber += "."
        secondNumberIsDouble = true
    }
}

fun isExpressionNotEmpty(expression: String) : Boolean{
    return !expression.isEmpty()
}
fun isSecondNumberNull() : Boolean{
    return secondNumber == ""
}
fun isThisNotRepeatedSign(expression: String, sign : String) : Boolean{
    return  expression.get(expression.length - 1).toString() != sign
}