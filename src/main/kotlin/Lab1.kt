fun main()
{
    val bigString: String = ("""
        |Сосисушу 
        |шеленциюле чка
        |Бурундучечка
    """).trimMargin()
    println(alignLeft(bigString, 10))
    println(alignRight(bigString, 10))
}

fun makeString(lines: List<String>): String{
    var result = ""
    for(i in lines) result = result +  i + "\n"
    return result
}

fun splitByWidth(rawString: String, length: Int): MutableList<String>
{
    val listOfLines = rawString.lines().toMutableList()
    var i = 0
    while(i<listOfLines.size)
    {
        if(listOfLines[i].length>length+1)
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
        val count = length - listOfLines[i].length
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

fun alignWidth(rawString: String, length: Int){
    val listOfLines = splitByWidth(rawString, length)
    //TODO
}

fun alignCenter(rawString: String, length: Int){
    val listOfLines = splitByWidth(rawString, length)
    //TODO
}



