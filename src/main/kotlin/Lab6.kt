class ShapeCollector<T : Shape> {
    private val allShapes = mutableListOf<T>()

    fun add(new: T) {
        allShapes.add(new)
    }

    fun addAll(new: List<T>) {
        allShapes.addAll(new)
    }

    fun getAll(): List<T> {
        return allShapes
    }

    fun getAllSorted(comparator: Comparator<in T>): List<T> {
        return allShapes.sortedWith(comparator)
    }

    //the name suggested by the development environment, I liked it
    fun getAllByClass(java: Class<out T>): List<T> {
        return allShapes.filterIsInstance(java)
    }
}

object ShapeComparators {
    val sortDSEC_area = compareBy<Shape> { -it.calcArea() }
    val sortASC_area = compareBy<Shape> { it.calcArea() }
    val sortDSEC_perimeter = compareBy<Shape> { -it.calcPerimeter() }
    val sortASC_perimeter = compareBy<Shape> { it.calcPerimeter() }
    val sortDSEC_radius = compareBy<Circle> { -it.radius }
    val sortASC_radius = compareBy<Circle> { it.radius }
}

//If the code does work, the examples in Main.kt