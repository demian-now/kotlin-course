fun main() {
    val a = MutableMatrix(arrayOf(arrayOf(1.0)))
    a /= 2.0
    a.cols
    a.rows
    a[1,0] = 2.0
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
        return array.contentToString()
    }

    operator fun get(i: Int, j: Int): Double {
        return array[i][j]
    }

    operator fun plus(other: Matrix): Matrix {
        checkForSimple(other)
        TODO()
    }

    operator fun minus(other: Matrix): Matrix {
        checkForSimple(other)
        TODO()
    }

    operator fun times(other: Matrix): Matrix {
        checkForMultiply(other)
        TODO()
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

    private fun checkForMultiply(other: Matrix) {
        if (this.cols != other.rows)
            throw IllegalArgumentException("These matrices have inappropriate dimensions")
    }

    private fun checkForSimple(other: Matrix) {
        if (this.cols != other.cols || this.rows != other.rows)
            throw IllegalArgumentException("These matrices have inappropriate dimensions")
    }
}

class MutableMatrix(sample: Array<Array<Double>>) : Matrix(sample) {
    operator fun plusAssign(other: Matrix) {
        TODO()
    }

    operator fun minusAssign(other: Matrix) {
        TODO()
    }

    operator fun timesAssign(other: Matrix) {
        TODO()
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
