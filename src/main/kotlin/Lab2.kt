import kotlin.math.*

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
                "+-/*^()".contains(i) -> {
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
        if(!isCorrect(calculate))
            throw IllegalArgumentException("Illegal symbols expected")
        return calculate
    }

    private fun ifFunction(input: MutableList<Pair<TypeOfElement, String>>)
    {
        for(i in 0 until input.size)
        {
            var countOfLeftBrackets = 0
            var tempCount = 0
            if(input[i].first == TypeOfElement.Function)
            {
                val temp = mutableListOf<Pair<TypeOfElement, String>>()
                for(j in i+1 until input.size)
                {
                    temp.add(input[j])
                    if(input[j].second=="(") countOfLeftBrackets++
                    if(input[j].second==")") countOfLeftBrackets--
                    if(countOfLeftBrackets==0) {
                        tempCount = j;
                        break
                    }
                }
                val subExp = getResult(makePrefixNotation(temp))
                val subResult = when(input[i].second){
                    "cos" -> cos(subExp)
                    "sin" -> sin(subExp)
                    "tg" -> tan(subExp)
                    "ctg" -> (1/tan(subExp))
                    "ln" -> ln(subExp)
                    "sqrt" -> sqrt(subExp)
                    else -> throw IllegalArgumentException("Illegal function")
                }
                for(k in i+1..tempCount)
                    input.removeAt(k)
                input[i]= Pair(TypeOfElement.Number, subResult.toString())
            }
        }
    }

    private fun makePrefixNotation(input: MutableList<Pair<TypeOfElement, String>>): MutableList<Pair<TypeOfElement, String>>
    {
        ifFunction(input)
        val stack = mutableListOf<Pair<TypeOfElement, String>>()
        val queue = mutableListOf<Pair<TypeOfElement, String>>()
        for(i in input)
        {
            //TODO prefix
        }
        return queue
    }

    private fun getResult(input: MutableList<Pair<TypeOfElement, String>>): Double{
        //TODO getResult
        return 0.0
    }
}
fun getPriority(operator: String): Int
{
    return when(operator)
    {
        "+","-" -> 1
        "*","/" -> 2
        "^" -> 3
        else -> 0
    }
}
private fun Prefix.isCorrect(input: MutableList<Pair<TypeOfElement, String>>): Boolean
{
    val listOfFunc = listOf("sin", "cos", "tg", "ctg", "ln", "sqrt")
    val listOfConst = listOf("pi", "e")
    for(i in input)
    {
        if(i.first==TypeOfElement.Function
            && !listOfFunc.contains(i.second))
            return false
        if(i.first==TypeOfElement.Number
            && (i.second.contains('p')||i.second.contains('e'))
            && !listOfConst.contains(i.second))
            return false
    }
    return true
}


private fun String.clear(): String {
    return this.drop(this.length)
}
