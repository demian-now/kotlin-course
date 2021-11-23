import org.testng.annotations.Test

import org.testng.Assert.*
import kotlin.math.PI

class CircleTest {

    @Test
    fun testCalcArea() {
        assertEquals(Circle(20.0).calcArea(), 20.0*20.0*PI)
    }

    @Test
    fun testCalcPerimeter() {
        assertEquals(Circle(20.0).calcPerimeter(), 20.0*2.0*PI)
    }

    @Test
    fun testCircleIncorrect(){
        assertThrows { Circle(-2.0) }
    }

    @Test
    fun testCreateCircle()
    {
        Circle(20.0)
    }
}

class SquareTest {

    @Test
    fun testCalcArea() {
        assertEquals(Square(20.0).calcArea(), 20.0*20.0)
    }

    @Test
    fun testCalcPerimeter() {
        assertEquals(Square(20.0).calcPerimeter(), 20.0*4)
    }

    @Test
    fun testSquareIncorrect(){
        assertThrows { Square(-2.0) }
    }

    @Test
    fun testCreateSquare()
    {
        Square(20.0)
    }
}

class RectangleTest {

    @Test
    fun testCalcArea() {
        assertEquals(Rectangle(20.0, 40.0).calcArea(), 20.0*40.0)
    }

    @Test
    fun testCalcPerimeter() {
        assertEquals(Rectangle(20.0, 40.0).calcPerimeter(), 120.0)
    }

    @Test
    fun testRectangleIncorrect(){
        assertThrows { Rectangle(-2.0, 0.1) }
    }

    @Test
    fun testCreateRectangle()
    {
        Rectangle(20.0, 40.0)
    }
}

class TriangleTest {

    @Test
    fun testCalcArea() {
        assertEquals(Triangle(3.0, 4.0, 5.0).calcArea(), 6.0)
    }

    @Test
    fun testCalcPerimeter() {
        assertEquals(Triangle(3.0, 4.0, 5.0).calcPerimeter(), 12)
    }

    @Test
    fun testRectangleIncorrect(){
        assertThrows { Triangle(-2.0, 0.1, 12.0) }
    }

    @Test
    fun testCreateRectangle()
    {
        Triangle(20.0, 40.0, 25.0)
    }
}


