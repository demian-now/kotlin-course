fun main() {
   val mmm = mutableListOf<Int>(1,2,3,4,5)
   delete(mmm)
   println(mmm)
}

fun delete(mmm: MutableList<Int>)
{
   mmm.removeAt(1)
}
