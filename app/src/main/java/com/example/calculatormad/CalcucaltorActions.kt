package com.example.calculatormad


fun calculatorAction(string: EnumAction){
    when(string){
        EnumAction.AbsolutelyClear -> AbsolutellyClear()
        EnumAction.DeleteLastSymbol -> DeleteLastSymbol()
        EnumAction.ChangeSign -> ChangeSign()
        EnumAction.Remainder -> Remainder()
        EnumAction.Add -> Add()
        EnumAction.Substract -> Substract()
        EnumAction.Multiply -> Multiply()
        EnumAction.Divide -> Divide()
        EnumAction.Answer -> Answer()
        else -> {
            if (string.ordinal in 0..9)
                AddNumber(string = string)
        }

    }




}


fun AddNumber(string : EnumAction) {
    TODO("Not yet implemented")
}


fun Answer() {
    TODO("Not yet implemented")
}


fun Divide() {
    TODO("Not yet implemented")
}


fun Multiply() {
    TODO("Not yet implemented")
}


fun Substract() {
    TODO("Not yet implemented")
}


fun Add() {
    TODO("Not yet implemented")
}


fun Remainder() {
    TODO("Not yet implemented")
}

fun ChangeSign() {
    TODO("Not yet implemented")
}


fun DeleteLastSymbol() {
    TODO("Not yet implemented")
}


fun AbsolutellyClear() {
    TODO("Not yet implemented")
}

fun addNumber(string: String) {
    TODO("Not yet implemented")
}