import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.random.Random

interface Shape {
    fun calcArea(): Double
    fun calcPerimeter(): Double
}

class Circle(val radius: Double) : Shape {
    init {
        if (radius < 0) throw IllegalArgumentException("Radius can't be negative")
    }

    override fun calcArea(): Double {
        return radius.pow(2) * PI
    }

    override fun calcPerimeter(): Double {
        return 2.0 * radius * PI
    }

    override fun toString(): String {
        return "Circle(radius=$radius, area = ${calcArea()}, perimeter = ${calcPerimeter()})"
    }
}

class Square(val sideLength: Double) : Shape {
    init {
        if (sideLength < 0) throw IllegalArgumentException("Radius can't be negative")
    }

    override fun calcArea(): Double {
        return sideLength.pow(2)
    }

    override fun calcPerimeter(): Double {
        return 4.0 * sideLength
    }

    override fun toString(): String {
        return "Square(sideLength=$sideLength, area = ${calcArea()}, perimeter = ${calcPerimeter()})"
    }

}

class Rectangle(
    val width: Double,
    val height: Double,
) : Shape {
    init {
        if ((width < 0) || (height < 0)) throw IllegalArgumentException("Rectangle parameters can't be negative")
    }

    override fun calcArea(): Double {
        return width * height
    }

    override fun calcPerimeter(): Double {
        return 2 * (width + height)
    }

    override fun toString(): String {
        return "Rectangle(width=$width, height=$height, area = ${calcArea()}, perimeter = ${calcPerimeter()})"
    }
}

class Triangle(
    val a: Double,
    val b: Double,
    val c: Double,
) : Shape {

    init {
        if (a < 0 && b < 0 && c < 0) throw IllegalArgumentException("Triangle parameters can't be negative")
        if (a + b < c || a + c < b || b + c < a) throw IllegalArgumentException("There is no triangle with these parameters")
    }

    override fun calcArea(): Double {
        val p = calcPerimeter() / 2.0
        return sqrt(p * (p - a) * (p - b) * (p - c))
    }

    override fun calcPerimeter(): Double {
        return a + b + c
    }

    override fun toString(): String {
        return "Triangle(a=$a, b=$b, c=$c, area = ${calcArea()}, perimeter = ${calcPerimeter()})"
    }

}

interface ShapeFactory {
    fun createCircle(radius: Double): Circle
    fun createSquare(side: Double): Square
    fun createRectangle(width: Double, height: Double): Rectangle
    fun createTriangle(a: Double, b: Double, c: Double): Triangle

    fun createRandomCircle(): Circle
    fun createRandomSquare(): Square
    fun createRandomRectangle(): Rectangle
    fun createRandomTriangle(): Triangle

    fun createRandomShape(): Shape
}

class ShapeFactoryImpl : ShapeFactory {

    override fun createCircle(radius: Double): Circle {
        return Circle(radius)
    }

    override fun createSquare(side: Double): Square {
        return Square(side)
    }

    override fun createRectangle(width: Double, height: Double): Rectangle {
        return Rectangle(width, height)
    }

    override fun createTriangle(a: Double, b: Double, c: Double): Triangle {
        return Triangle(a, b, c)
    }

    override fun createRandomCircle(): Circle {
        return createCircle(Random.nextDouble(0.0, Int.MAX_VALUE.toDouble()))
    }

    override fun createRandomSquare(): Square {
        return createSquare(Random.nextDouble(0.0, Int.MAX_VALUE.toDouble()))
    }

    override fun createRandomRectangle(): Rectangle {
        return createRectangle(Random.nextDouble(0.0, Int.MAX_VALUE.toDouble()),
            Random.nextDouble(0.0, Int.MAX_VALUE.toDouble()))
    }

    override fun createRandomTriangle(): Triangle {
        val a = Random.nextDouble(0.0, Int.MAX_VALUE.toDouble())
        val b = Random.nextDouble(0.0, Int.MAX_VALUE.toDouble())
        val c = Random.nextDouble(abs(b - a), a + b)
        return createTriangle(a, b, c)
    }

    override fun createRandomShape(): Shape {
        return when (Random.nextInt(0, 3)) {
            0 -> createRandomCircle()
            1 -> createRandomSquare()
            2 -> createRandomRectangle()
            3 -> createRandomTriangle()
            else -> error("If this happened, then the world is lost")
        }
    }
}