fun main() {
   val test1 = """
        |Я делал эту лабу 
        |вроде бы и два дня, но как бы три, хотя чистого времени ушло примерно часа 3""".trimMargin()
   println(alignText(test1, 1, Alignment.LEFT))

   val test2 = Prefix()

   val easy = "1+2+3+4+5+6   +7"
   val medium = "1*2+3*(12/1)"
   val hard = "2*cos(pi/2)/0.01-(-1*sin(cos(2-tg(3))))"
   val hardcore = "2^8-12345*10000"

   println(easy + "=" + test2.calculate(easy))
   println(medium + "=" + test2.calculate(medium))
   println(hard + "=" + test2.calculate(hard))
   println(hardcore + "=" + test2.calculate(hardcore))
}

