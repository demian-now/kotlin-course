import kotlin.math.PI
import kotlin.math.pow
import kotlin.random.Random

interface Shape {
    fun calcArea(): Double
    fun calcPerimeter(): Double
}

class Circle(_radius: Double) : Shape{

    private var radius = _radius
    init {
        if (_radius < 0) throw IllegalArgumentException("Radius can't be negative")
    }

    override fun calcArea(): Double {
        return radius.pow(2)* PI
    }

    override fun calcPerimeter(): Double {
        return 2.0*radius* PI
    }
}

class Square(_sideLength: Double) : Shape{
    private var sideLength: Double = _sideLength

    init {
        if(_sideLength<0) throw IllegalArgumentException("Radius can't be negative")
    }
    override fun calcArea(): Double {
        return sideLength.pow(2)
    }

    override fun calcPerimeter(): Double {
        return 4.0 * sideLength
    }

}

class Rectangle(
    _width: Double,
    _height: Double
) :  Shape{
    private var width = _width
    private var height = _height

    init {
        if((width<0) && (height<0)) throw IllegalArgumentException("Rectangle parameters can't be negative")
    }

    override fun calcArea(): Double {
        return width*height
    }

    override fun calcPerimeter(): Double {
        return 2*(width+height)
    }
}

class Triangle(_a: Double, _b: Double, _c:Double):Shape{
    private var a = _a
    private var b = _b
    private var c = _c

    init {
        if(_a<0 && _b<0 && _c<0) throw IllegalArgumentException("Triangle parameters can't be negative")
        if(a+b>c || a+c>b || b+c>a) throw IllegalArgumentException("There is no triangle with these parameters")
    }

    override fun calcArea(): Double {
        val p = calcPerimeter()/2.0
        return p*(p-a)*(p-b)*(p-c)
    }

    override fun calcPerimeter(): Double {
        return a+b+c
    }

}

interface ShapeFactory {
    fun createCircle(radius: Double): Circle
    fun createSquare(side: Double): Square
    fun createRectangle(width:Double, height: Double): Rectangle
    fun createTriangle(a:Double, b:Double, c:Double): Triangle

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
    override fun createRectangle(width:Double, height: Double): Rectangle
    {
        return Rectangle(width, height)
    }
    override fun createTriangle(a:Double, b:Double, c:Double): Triangle
    {
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
        var a: Double
        var b: Double
        var c: Double
        while(true)
        {
            a = Random.nextDouble(0.0, Int.MAX_VALUE.toDouble())
            b = Random.nextDouble(0.0, Int.MAX_VALUE.toDouble())
            c = Random.nextDouble(0.0, Int.MAX_VALUE.toDouble())
            if(a+b>c && a+c>b && b+c>a)
                break
        }
        return createTriangle(a,b,c)
    }

    override fun createRandomShape(): Shape {
        return when(Random.nextInt(0, 3)){
            0-> createRandomCircle()
            1-> createRandomSquare()
            2-> createRandomRectangle()
            3-> createRandomTriangle()
            else -> error("If this happened, then the world is lost")
        }
    }
}