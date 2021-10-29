fun main() {
   val temp = readLine()?.toInt()
   val list = mutableListOf<Int>()
   var result: Double = 0.0
   var input = readLine()
   for(i in 0 until temp!!) {
      input?.substringBefore(' ')?.toInt()?.let { list.add(it) }
      input = input?.substringAfter(' ')
   }
   for(i in 0 until temp!!-1)
   {
      list.sort()
      result+=(list[0]+list[1])*0.05
      list[1] = list[0]+list[1]
      list.removeAt(0)
   }
   println(result)
}