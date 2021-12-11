fun main() {
    val a = Matrix(arrayOf(arrayOf(1.0, 2.0, 3.0), arrayOf(4.0,5.0,6.0)))
    println(a.cols)
    println(a.rows)
    println(a.toString())
    println(a.transpose().toString())
    println((a*a.transpose()).toString())
}


open class Matrix(sample: Array<Array<Double>>) {
    protected var array = arrayOf(arrayOf(1.0))

    init {
        array = sample.clone()
        val control = array[0].size
        for (i in array)
            if (i.size != control) throw IllegalArgumentException("The rows of the matrix have different dimensions")
    }

    val rows: Int
        get() = array.size
    val cols: Int
        get() = array[0].size

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Matrix) return false

        if (!array.contentDeepEquals(other.array)) return false
        return true
    }

    override fun hashCode(): Int {
        return array.contentDeepHashCode()
    }

    override fun toString(): String {
        var result = ""
        for(i in array.indices) {
            for (j in array[0].indices)
                result+= (" ${array[i][j]}")
            result+='\n'
        }
         return result
    }

    operator fun get(i: Int, j: Int): Double {
        return array[i][j]
    }

    operator fun plus(other: Matrix): Matrix {
        checkForSimple(other)
        val result = array.clone()
        for(i in result.indices)
            for(j in result[0].indices)
                result[i][j]+=other[i,j]
        return Matrix(result)
    }

    operator fun minus(other: Matrix): Matrix {
        checkForSimple(other)
        val result = array.clone()
        for(i in result.indices)
            for(j in result[0].indices)
                result[i][j]+=other[i,j]
        return Matrix(result)
    }

    operator fun times(other: Matrix): Matrix {
        checkForMultiply(other)
        val result : Array<Array<Double>> = Array(rows) { Array(other.cols) { 0.0 } }
        for(i in result.indices)
            for(j in result[0].indices)
                for(k in 0 until cols)
                    result[i][j] += (array[i][k] * other[k,j])
        return Matrix(result)
    }

    operator fun plus(scalar: Double): Matrix {
        val result = array.clone()
        for(i in result.indices)
           for(j in result[0].indices)
               result[i][j]+=scalar
        return Matrix(result)
    }

    operator fun minus(scalar: Double): Matrix {
        val result = array.clone()
        for(i in result.indices)
            for(j in result[0].indices)
                result[i][j]-=scalar
        return Matrix(result)
    }

    operator fun times(scalar: Double): Matrix {
        val result = array.clone()
        for(i in result.indices)
            for(j in result[0].indices)
                result[i][j]*=scalar
        return Matrix(result)
    }

    operator fun div(scalar: Double): Matrix {
        if(scalar==0.0) {
            throw IllegalArgumentException("Сan't be divided by zero")
        }
        val result = array.clone()
        for(i in result.indices)
            for(j in result[0].indices)
                result[i][j]/=scalar
        return Matrix(result)
    }

    fun transpose(): Matrix
    {
        val result: Array<Array<Double>> = Array(cols) { Array(rows) { 0.0 } }
        for(i in array.indices)
            for(j in array[0].indices)
                result[j][i] = array[i][j]
        return Matrix(result)
    }

    protected fun checkForMultiply(other: Matrix) {
        if (this.cols != other.rows)
            throw IllegalArgumentException("These matrices have inappropriate dimensions")
    }

    protected fun checkForSimple(other: Matrix) {
        if (this.cols != other.cols || this.rows != other.rows)
            throw IllegalArgumentException("These matrices have inappropriate dimensions")
    }
}

class MutableMatrix(sample: Array<Array<Double>>) : Matrix(sample) {
    operator fun plusAssign(other: Matrix) {
        checkForSimple(other)
        for(i in array.indices)
            for(j in array[0].indices)
                array[i][j]+=other[i,j]
    }

    operator fun minusAssign(other: Matrix) {
        checkForSimple(other)
        for(i in array.indices)
            for(j in array[0].indices)
                array[i][j]-=other[i,j]
    }

    operator fun timesAssign(other: Matrix) {
        checkForMultiply(other)
        val result : Array<Array<Double>> = Array(rows) { Array(other.cols) { 0.0 } }
        for(i in result.indices)
            for(j in result[0].indices)
                for(k in 0 until cols)
                    result[i][j] += (array[i][k] * other[k,j])
        array = result
    }

    operator fun plusAssign(scalar: Double) {
        for(i in array.indices)
            for(j in array[0].indices)
                array[i][j]+=scalar
    }

    operator fun minusAssign(scalar: Double) {
        for(i in array.indices)
            for(j in array[0].indices)
                array[i][j]-=scalar
    }

    operator fun timesAssign(scalar: Double) {
        for(i in array.indices)
            for(j in array[0].indices)
                array[i][j]*=scalar
    }

    operator fun divAssign(scalar: Double) {
        if(scalar==0.0) {
            throw IllegalArgumentException("Сan't be divided by zero")
        }
        for(i in array.indices)
            for(j in array[0].indices)
                array[i][j]/=scalar
    }

    operator fun unaryMinus() {
        this.timesAssign(-1.0)
    }

    operator fun unaryPlus() = //let's imagine that there is at least some practical benefit in this function
        Unit

    operator fun set(i: Int, j: Int, value: Double) {
        array[i][j] = value
    }
}
