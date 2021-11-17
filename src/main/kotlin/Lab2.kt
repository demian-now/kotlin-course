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

class Prefix
{
    fun calculate(input: String): Double{
        val listOfElement = getListOfElements(input)
        val mathExpr = makePrefixNotation(listOfElement)
        return getResult(mathExpr)
    }
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
                    "1234567890.pie".contains(i) -> {
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
                    "1234567890.pie".contains(i) -> temp+=i
                    "sincostgctglnsqrt".contains(i) -> {
                        calculate.add(Pair(typeOf, temp))
                        temp = temp.clear()
                        temp+=i
                        typeOf = TypeOfElement.Function
                    }
                }
                typeOf == TypeOfElement.Function -> when{
                    "sincostgctglnsqrt".contains(i) -> temp+=i
                    "1234567890.pie".contains(i) -> {
                        calculate.add(Pair(typeOf, temp))
                        temp = temp.clear()
                        temp += i
                        typeOf = TypeOfElement.Number
                    }
                }
            }
        calculate.add(Pair(typeOf, temp))
        if(!isCorrect(calculate))
            throw IllegalArgumentException("Illegal symbols expected")
        unaryOperator(calculate)
        return calculate
    }

    private fun ifFunction(input: MutableList<Pair<TypeOfElement, String>>)
    {
        var isChanged = true
        while(isChanged)
            for(i in 0 until input.size)
            {
                isChanged = false
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
                            tempCount = j
                            break
                        }
                    }
                    temp.removeFirst()
                    temp.removeLast()
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
                        input.removeAt(i+1)
                    input[i]= Pair(TypeOfElement.Number, subResult.toString())
                    isChanged=true
                    break
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
            if(i.first == TypeOfElement.Number)
                queue.add(i)
            else if(i.first == TypeOfElement.Operator) {
                if (stack.isEmpty() || stack.last().second == "(")
                    stack.add(i)
                else if(getPriority(i.second)>getPriority(stack.last().second))
                    stack.add(i)
                else {
                    while(stack.isNotEmpty() && (stack.last().second!="(" || (getPriority(stack.last().second)<getPriority(i.second))))
                    {
                        queue.add(stack.last())
                        stack.removeLast()
                    }
                    stack.add(i)
                }
            }
            if(i.second == "(")
                stack.add(i)
            if(i.second == ")") {
                while(stack.isNotEmpty()&&stack.last().second!="(")
                {
                    queue.add(stack.last())
                    stack.removeLast()
                }
                if(stack.isNotEmpty()) stack.removeLast()
            }
        }
        while(stack.isNotEmpty())
        {
            queue.add(stack.last())
            stack.removeLast()
        }
        queue.reverse()
        return queue
    }
    private fun isCorrect(input: MutableList<Pair<TypeOfElement, String>>): Boolean
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
    private fun unaryOperator(input: MutableList<Pair<TypeOfElement, String>>)
    {
        if("+-".contains(input[0].second))
            input.add(0, Pair(TypeOfElement.Number, "0"))
        for(i in 0 until input.size)
        {
            if(i+1<input.size)
            {
                if(input[i].second=="(" && "+-".contains(input[i+1].second))
                    input.add(i+1, Pair(TypeOfElement.Number, "0"))
            }
        }
    }
    private fun getResult(input: MutableList<Pair<TypeOfElement, String>>): Double{
        while(input.size>1) {
            var isChanged = false
            for (i in 0..input.size - 3) {
                if (input[i].first == TypeOfElement.Operator
                    && input[i + 1].first == TypeOfElement.Number
                    && input[i + 2].first == TypeOfElement.Number
                ) {
                    val value1 = if("pie".contains(input[i+1].second)) 
                        if(input[i+1].second=="pi") pi else e else input[i+1].second.toDouble()
                    val value2 = if("pie".contains(input[i+2].second)) if(input[i+2].second=="pi") pi else e else input[i+2].second.toDouble()
                    input[i] = Pair(
                        TypeOfElement.Number, when (input[i].second) {
                            "+" -> (value2 + value1).toString()
                            "-" -> (value2 - value1).toString()
                            "*" -> (value2 * value1).toString()
                            "/" -> (value2 / value1).toString()
                            "^" -> value2.pow(value1).toString()
                            else -> throw IllegalArgumentException("Illegal operator")
                        }
                    )
                    isChanged = true
                    input.removeAt(i+1)
                    input.removeAt(i+1)
                    break
                }
            }
            if (!isChanged)
                throw IllegalArgumentException("Illegal expression")
        }
        return if("pie".contains(input[0].second)) if(input[0].second=="pi") pi else e else input[0].second.toDouble()
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

