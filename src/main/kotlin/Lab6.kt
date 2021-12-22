class ShapeCollector<T : Shape> {
    private val allShapes = mutableListOf<T>()

    fun add(new: T) /* ??? */ {}
    fun addAll(new: List<T>) {}
    fun getAll(): List<T> {
        TODO()
    }

    fun getAllSorted(comparator: /* ??? */): List<T> {
        TODO()
    }
}
