fun main()
{
    val test = """
        |1 2 3 4 5 6 7 8
        |1 2 3 4 5 6 7
        |1 2 3 4 5 6
        |1 2 3 4 5
        |1 2 3 4
        |1 2 3
        |1 2
        |1""".trimMargin()
    println(alignRight(test,20))
    println(alignLeft(test, 20))
    println(alignCenter(test, 20))
    println(alignWidth(test, 20))
}

fun makeString(lines: List<String>): String{
    var result = ""
    for(i in lines) result = result +  i + "\n"
    return result
}

fun splitByWidth(rawString: String, length: Int): MutableList<String>
{
    if(length<=0)
        throw IllegalArgumentException("Length can't be 0 or negative")
    val listOfLines = rawString.lines().toMutableList()
    var i = 0
    while(i<listOfLines.size)
    {
        if(listOfLines[i].length>length)
        {
            val firstPart = listOfLines[i].substring(0, length)
            var secondPart = listOfLines[i].substring(length)
            secondPart = if(firstPart.contains(' ')) firstPart.substringAfterLast(' ') + secondPart else secondPart
            listOfLines[i] = firstPart.substringBeforeLast(' ')
            listOfLines.add(i+1, secondPart)
        }
        i++
    }
    return listOfLines
}

fun alignRight(rawString: String, length: Int): String {
    val listOfLines = splitByWidth(rawString, length)
    for (i in 0 until listOfLines.size) {
        listOfLines[i] = listOfLines[i].trim()
        val count = length - listOfLines[i].length + 1
        listOfLines[i] = "".padStart(count, ' ') + listOfLines[i].trim()
    }
    return makeString(listOfLines)
}

fun alignLeft(rawString: String, length: Int): String {
    val listOfLines = splitByWidth(rawString, length)
    for (i in 0 until listOfLines.size)
        listOfLines[i] = listOfLines[i].trim()
    return makeString(listOfLines)
}

fun alignWidth(rawString: String, length: Int): String{
    val listOfLines = splitByWidth(rawString, length)
    for(i in 0 until listOfLines.size) {
        listOfLines[i] = listOfLines[i].trim()
        val count = listOfLines[i].countOfSym(' ')
        val countOfSpaces = length - listOfLines[i].length
        //TODO Пересчитать в трезвом уме
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
        listOfLines[i] = listOfLines[i].trim()
        val count = length - listOfLines[i].length
        listOfLines[i] = "".padStart(count/2, ' ') + listOfLines[i].trim() + "".padStart(count/2, ' ')
    }
    return makeString(listOfLines)
}



