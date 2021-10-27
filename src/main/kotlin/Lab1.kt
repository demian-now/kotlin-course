fun main()
{
    val test = """
        |Я делал эту лабу вроде бы и два дня, но как бы три, хотя чистого времени ушло примерно часа 3""".trimMargin()
    println(alignRight(test,20))
    println(alignLeft(test, 20))
    println(alignCenter(test, 20))
    println(alignWidth(test, 20))
    println(alignText(test, 20, Alignment.LEFT))
}

enum class Alignment {
    LEFT,
    RIGHT,
    CENTER,
    JUSTIFY
}

fun alignText(text: String, lineWidth: Int, alignment: Alignment): String {
    return when(alignment){
        Alignment.LEFT -> alignLeft(text, lineWidth)
        Alignment.RIGHT -> alignRight(text, lineWidth)
        Alignment.CENTER -> alignCenter(text, lineWidth)
        Alignment.JUSTIFY -> alignWidth(text, lineWidth)
    }
}


fun makeString(lines: List<String>): String{
    var result = ""
    for(i in lines) result = result +  i + "\n"
    return result
}

fun splitByWidth(rawString: String, length: Int): MutableList<String>
{
    if(length<=0)
        throw IllegalArgumentException("Length can't be 0 or negative") //as a precaution
    val listOfLines = rawString.lines().toMutableList()
    var i = 0
    while(i<listOfLines.size)
    {
        if(listOfLines[i].length>length) //here the words are transferred to another line, taking into account punctuation marks
        {
            val firstPart = listOfLines[i].substring(0, length)
            var secondPart = listOfLines[i].substring(length)
            secondPart = if(firstPart.contains(' ')) firstPart.substringAfterLast(' ') + secondPart else secondPart
            listOfLines[i] = firstPart.substringBeforeLast(' ')
            listOfLines.add(i+1, secondPart)
        }
        listOfLines[i] = listOfLines[i].trim()
        i++
    }
    return listOfLines
}

fun alignRight(rawString: String, length: Int): String {
    val listOfLines = splitByWidth(rawString, length)
    for (i in 0 until listOfLines.size) {
        val count = length - listOfLines[i].length + 1
        listOfLines[i] = "".padStart(count, ' ') + listOfLines[i].trim()
    }
    return makeString(listOfLines)
}

fun alignLeft(rawString: String, length: Int): String {
    val listOfLines = splitByWidth(rawString, length)
    return makeString(listOfLines)
}

fun alignWidth(rawString: String, length: Int): String{
    val listOfLines = splitByWidth(rawString, length)
    for(i in 0 until listOfLines.size-1) { //when the text is aligned to the width, the last line does not change
        val count = listOfLines[i].countOfSym(' ')
        val countOfSpaces = length - listOfLines[i].length
        if(count!=0 && countOfSpaces!=0)
        {
            val tempCount1 = (count+countOfSpaces)/count+1
            val tempCount2 = count - countOfSpaces%count
            listOfLines[i] = listOfLines[i].replace(" ", "".padStart(tempCount1, ' '))
            for(j in 0 until tempCount2)
                listOfLines[i] = listOfLines[i].replaceFirst("".padStart(tempCount1, ' '), "".padStart(tempCount1-1, ' '))
        }
    }
    return makeString(listOfLines)
}

private fun String.countOfSym(symbol: Char): Int {
    var count =0
    for(i in this)
        if(i==symbol) count++
    return count
}

fun alignCenter(rawString: String, length: Int): String{
    val listOfLines = splitByWidth(rawString, length)
    for (i in 0 until listOfLines.size) {
        val count = length - listOfLines[i].length
        listOfLines[i] = "".padStart(count/2, ' ') + listOfLines[i].trim() + "".padStart(count/2, ' ')
    }
    return makeString(listOfLines)
}



