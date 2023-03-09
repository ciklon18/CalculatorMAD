package com.example.calculatormad

import androidx.compose.runtime.MutableState

// число максимум 9 символов, не учитывая точку, +- и просто знак
// нужно проверять на деление на 0
// нужно проверять на то, что введен ли какой-то символ
// нужно сделать проверку на длину. Строка должна быть менее 12 ссимволов, иначе можно использовать только AllClean, DeleteLastSymbol, Answer
// для строки ввода отдельная проверка
// для удаления последнего символа смена изменение double может быть, удаление знака ну и изменение самого числа
// добавить по умолчанию значение, равное нулю


val signs = arrayOf("+","-","*","/")
var signExist = false
var signWasChanged = false
var firstNumberIsDouble = false
var secondNumberIsDouble = false
var firstNumber = "0"
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


fun buttonAnswer(expression : MutableState<String>) { // если не введен никакой символ то ответ 0
    if (signs.contains(currentSign)){
        var result = 0.0
        if (secondNumber != "")
        {
            if (secondNumber.get(secondNumber.length - 1) == '.'){
                secondNumber = secondNumber.substring(0, secondNumber.length - 1)
            }
            if (currentSign == "*"){
                result = firstNumber.toDouble() * secondNumber.toDouble()
            } else if (currentSign == "/"){
                result = firstNumber.toDouble() / secondNumber.toDouble() // проверка деления на ноль
            }else if (currentSign =="+"){
                result = firstNumber.toDouble() + secondNumber.toDouble()
            }else if (currentSign == "-"){
                result = firstNumber.toDouble() - secondNumber.toDouble() // ДОБАВИТЬ %
            }
            if (result == 0.0){
                expression.value = "0"
                firstNumber = "0"
            } else{
                expression.value = result.toString() // добавить случай, когда результат после точке равен x.0, то перводить в int
                firstNumber = result.toString()
            }

            signWasChanged = if (expression.value.get(0) == '-') true else false
            secondNumber = ""
        } else{
            if (firstNumber.get(firstNumber.length - 1) == '.'){
                firstNumber = firstNumber.substring(0, firstNumber.length - 1)
            }
            if (firstNumber != ""){
                expression.value = firstNumber
                signWasChanged = if (expression.value.get(0) == '-') true else false
            } else{
                expression.value = "0"
                firstNumber = ""
                signWasChanged = false
            }
        }
        firstNumberIsDouble = false // проверить момент того, когда нужно изменять флаг double
        signExist = false
        secondNumberIsDouble = false
        currentSign = ""

    } else {
        if (firstNumber.get(firstNumber.length - 1) == '.'){
            firstNumber = firstNumber.substring(0, firstNumber.length - 1)
        }
        expression.value = if (firstNumber != "") firstNumber else "0.0"
        signWasChanged = if  (expression.value.get(0) == '-') true else false
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
    if (firstNumber != "" && firstNumber != "0" && !signExist){
        if (!signWasChanged){
            firstNumber = "-" + firstNumber
            expression.value = firstNumber
            signWasChanged = true
        } else if (firstNumber != ""){
            firstNumber = firstNumber.substring(1, firstNumber.length)
            expression.value = firstNumber
            signWasChanged = false
        }
    }

}


fun isSignOrDoubleSymbol(isContain : String){ // перепроверить логику здесь
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
fun checkingTheEnteredCharacters(expression : String){ // перепроверить логику здесь
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
        firstNumber = firstNumber.substring(0, firstNumber.length - 1) // добавить случай, когда если 1 число имеет вид: -x, то удалить 2 символа
        isSignOrDoubleSymbol(isContain)
    }
}
fun buttonDeleteLastSymbol(expression : MutableState<String>){// добавить проверку на то, что если удалил одну единственную цифру 1 числа то удалить знак минус, если он был перед ним
    if (!expression.value.isEmpty() && expression.value != "0"){

        var currValue = expression.value
        if (!signExist && currValue.length == 2 && currValue.get(0) == '-'){
            firstNumber = "0"
            signWasChanged = false
        } else {
            checkingTheEnteredCharacters(currValue)
        }

        expression.value = if (firstNumber != "0") currValue.substring(0, currValue.length - 1) else "0"
    }

}

fun buttonAllClean(expression : MutableState<String>) {
    expression.value = "0"
    signExist = false
    signWasChanged = false
    firstNumberIsDouble = false
    secondNumberIsDouble = false
    firstNumber = "0"
    secondNumber = ""
    currentSign = ""
}

fun buttonDouble(expression: MutableState<String>) { // сделать проверку на то, чтобы не было повторных точек

    if (signExist && !secondNumberIsDouble){
        expression.value += "."
        secondNumber += "."
        secondNumberIsDouble = true
    } else if(!firstNumberIsDouble){
        expression.value += "."
        firstNumber += "."
        firstNumberIsDouble = true
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