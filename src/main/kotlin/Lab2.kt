const val pi = 3.1415926535897932384626433832795
const val e = 2.7182818284

enum class TypeOfElement{
    Operator,
    Number,
    Function,
    Bracket,
    NotDefined
}

fun main()
{
    var test = Prefix()
}

class Prefix
{
    private fun getListOfElements(input: String): MutableList<Pair<TypeOfElement, String>> {
        var temp = ""
        var typeOf = TypeOfElement.NotDefined
        val calculate = mutableListOf<Pair<TypeOfElement, String>>()
        for(i in input)
            when {
                "+-/*()".contains(i) -> {
                    calculate.add(Pair(typeOf, temp))
                    temp = temp.clear()
                    calculate.add(Pair(if("()".contains(i)) TypeOfElement.Bracket else TypeOfElement.Operator, "$i"))
                    typeOf = TypeOfElement.NotDefined
                }
                typeOf == TypeOfElement.NotDefined -> when {
                    "1234567890,pie".contains(i) -> {
                        temp+=i
                        typeOf = TypeOfElement.Number
                    }
                    "sincostgctglnsqrt".contains(i) -> {
                        temp+=i
                        typeOf = TypeOfElement.Function
                    }
                    else -> throw IllegalArgumentException("Illegal symbol: $i")
                }
                typeOf == TypeOfElement.Number -> when{
                    "1234567890,pie".contains(i) -> temp+=i
                    "sincostgctglnsqrt".contains(i) -> {
                        calculate.add(Pair(typeOf, temp))
                        temp = temp.clear()
                        temp+=i
                        typeOf = TypeOfElement.Function
                    }
                }
                typeOf == TypeOfElement.Function -> when{
                    "sincostgctglnsqrt".contains(i) -> temp+=i
                    "1234567890,pie".contains(i) -> {
                        calculate.add(Pair(typeOf, temp))
                        temp = temp.clear()
                        temp += i
                        typeOf = TypeOfElement.Number
                    }
                }
            }
        return calculate
    }
    //TODO More of shit
}

private fun String.clear(): String {
    return this.drop(this.length)
}
