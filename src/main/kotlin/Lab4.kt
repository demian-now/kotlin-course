open class Matrix(sample: Array<Array<Double>>)
{
    protected var array = arrayOf(arrayOf(1.0))

    init{
        array = sample.clone()
        val control = array[0].size
        for(i in array)
            if(i.size!=control) throw IllegalArgumentException("The rows of the matrix have different dimensions")
    }

    protected var rows = array.size
        get() = field
    protected var cols = array[0].size
        get() = field

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
        return "Matrix(array=${array.contentToString()})"
    }

    operator fun get(i: Int, j: Int): Double {
        TODO("Not yet implemented")
    }
    operator fun plus(other: Matrix): Matrix {
        TODO()
    }
    operator fun minus(other: Matrix): Matrix{
        TODO()
    }
    operator fun times(other: Matrix): Matrix {
        TODO()
    }
    operator fun plus(scalar: Double): Matrix {
        TODO()
    }
    operator fun minus(scalar: Double): Matrix{
        TODO()
    }
    operator fun times(scalar: Double): Matrix {
        TODO()
    }

    fun checkForMultiply(other: Matrix){
        if(this.cols != other.rows)
            throw IllegalArgumentException("These matrices have inappropriate dimensions")
    }

    fun checkForUnary(other: Matrix){
        if(this.cols != other.cols || this.rows != other.rows)
            throw IllegalArgumentException("These matrices have inappropriate dimensions")
    }
}

class MutableMatrix(sample: Array<Array<Double>>): Matrix(sample) {
    operator fun plusAssign(other: Matrix) {
        TODO()
    }
    operator fun minusAssign(other: Matrix) {
        TODO()
    }
    operator fun timesAssign(other: Matrix){
        TODO()
    }
    operator fun plusAssign(scalar: Double) {
        TODO()
    }
    operator fun minusAssign(scalar: Double){
        TODO()
    }
    operator fun timesAssign(scalar: Double){
        TODO()
    }
    operator fun unaryMinus(){
        TODO()
    }
    operator fun unaryPlus(){
        TODO()
    }
}
