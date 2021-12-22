fun main() {
    /* Наработки первых двух лаб*/

    // val test1 = """
    // |Я делал эту лабу
    // |вроде бы и два дня, но как бы три, хотя чистого времени ушло примерно часа 3""".trimMargin()
    // println(alignText(test1, 1, Alignment.LEFT))
    //
    // //здесь не было бага с посторонним пробелом перед новой строкой
    // //просто в самой строке после слова "лабу" стоял пробел
    // //если запустить сейчас, то баг и не существует
    //
    // val test2 = Prefix()
    //
    // val easy = "1+2+3+4+5+6   +7"
    // val medium = "1*2+3*(12/1)"
    // val hard = "2*cos(pi/2)/0.01-(-1*sin(cos(2-tg(3))))"
    // val hardcore = "2^8-12345*10000"
    //
    // println(easy + "=" + test2.calculate(easy))
    // println(medium + "=" + test2.calculate(medium))
    // println(hard + "=" + test2.calculate(hard))
    // println(hardcore + "=" + test2.calculate(hardcore))
    // //всё-таки MutableList в моей реализации необходим, потому что я добавляю и удаляю элементы из списка
    // //и реализовать мой алгоритм с помощью создания новых изменённых списков было бы крайне затруднительно
    //
    // /*val listOfShape = mutableListOf<Shape>()
    // for(i in 0..9)
    // listOfShape.add(ShapeFactoryImpl().createRandomShape())
    // listOfShape.forEach {
    // when(it){
    // is Circle -> print("Circle with ")
    // is Square -> print("Square with ")
    // is Rectangle -> print("Rectangle with ")
    // is Triangle -> print("Triangle with ")
    // }
    // println("area: ${it.calcArea()}, perimeter: ${it.calcPerimeter()}")
    // }*/
    //
    // val realListOfShape = listOf<Shape>(
    // Circle(20.0),
    // Triangle(12.0, 13.0, 14.0),
    // Square(15.0),
    // Rectangle(12.0,13.0),
    // Circle(PI),
    // Circle(12.0),
    // Square(23.0),
    // Triangle(1.0,32.5, 33.0)
    // )
    //
    // var sumArea = 0.0
    // var sumPer = 0.0
    //
    // var maxArea: Shape = Circle(0.0)
    // var maxPer: Shape = Square(0.0)
    //
    // realListOfShape.forEach {
    // when(it){
    // is Circle -> print("Circle with ")
    // is Square -> print("Square with ")
    // is Rectangle -> print("Rectangle with ")
    // is Triangle -> print("Triangle with ")
    // }
    // println("area: ${it.calcArea()}, perimeter: ${it.calcPerimeter()}")
    // }
    //
    // realListOfShape.forEach{
    // sumArea+=it.calcArea()
    // sumPer+=it.calcPerimeter()
    // if(it.calcArea()>maxArea.calcArea())
    // maxArea = it
    // if(it.calcPerimeter()>maxPer.calcPerimeter())
    // maxPer = it
    // }
    // println("sum of areas $sumArea")
    // println("sum of perimeters $sumPer")
    // println("max area is ${
    // when (maxArea) {
    // is Circle -> "Circle"
    // is Square -> "Square"
    // is Rectangle -> "Rectangle"
    // is Triangle -> "Triangle"
    // else -> error("")
    // }
    // } with ${maxArea.calcArea()}")
    // println("max perimeter is ${
    // when (maxPer) {
    // is Circle -> "Circle"
    // is Square -> "Square"
    // is Rectangle -> "Rectangle"
    // is Triangle -> "Triangle"
    // else -> error("")
    // }
    // } with ${maxArea.calcPerimeter()}")

    val test5 = LibraryServiceImpl()

    val book1 = Book(
        title = "Муму",
        author = Author("Иван", "Сергеевич", "Тургенев"),
        year = 1852,
        genre = Genre.Drama,
        status = Status.Available
    )

    val book2 = Book(
        title = "Хранители",
        author = Author("Алан", "", "Мур"),
        year = 1987,
        genre = Genre.Comics,
        status = Status.Available
    )

    val book3 = Book(
        title = "Neon Genesis Evangelion",
        author = Author("Ёсиюки", "", "Садамото"),
        year = 1995,
        genre = Genre.Tragedy,
        status = Status.Restoration
    )

    val book4 = Book(
        title = "Call Me by Your Name",
        author = Author("Андре", "", "Асиман"),
        year = 2007,
        genre = Genre.Drama,
        status = Status.ComingSoon
    )

    test5.addBook(book1)
    test5.addBook(book2)
    test5.addBook(book3)
    test5.addBook(book4)

    println(test5.findBooks(genre = Genre.Drama))
    println(test5.getAllAvailableBooks())

    val test5map = test5.getAllBookStatuses()

    println(test5map.toString())

    val arr = arrayOf(
        arrayOf(1.0, 2.0),
        arrayOf(1.0, 2.0)
    )
    val matrix = Matrix(arr)

    arr[0][0] = 100.0
    println(matrix)
}

