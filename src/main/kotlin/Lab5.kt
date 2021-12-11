import java.util.*

typealias Year = Int

data class Book(
    val title: String,
    val isbn: String = "No isbn",
    val author: Author,
    val year: Year = 1900,
    val genre: Genre,
    val description: String = "No description"
)

data class Author(
    val firstName: String,
    val middleName: String,
    val lastName: String
)

class User(
    val firstName: String,
    val middleName: String,
    val lastName: String,
    val registrationDate: Date,
    val rentedBooks: List<Book>,
    val isActive: Boolean = true
)
{
    fun addBook(book: Book)
    {
        TODO()
    }

    fun removeBook(book: Book)
    {
        TODO()
    }
}

enum class Genre {
    Adventure,
    Fiction,
    Detective,
    Drama,
    Comedy,
    Romance,
    Tragedy,
    Scientific,
    Biography,
    Historical,
    Children,
    Comics,
    Magazine,
    Encyclopedia,
    Epic,
    Erotica,
    Medicine,
    Educational
}

sealed class Status {
    object Available : Status()
    data class UsedBy(val user: User) : Status()
    object ComingSoon : Status()
    object Restoration : Status()
}

interface LibraryService {
    fun findBooks(substring: String): List<Book>
    fun findBooks(author: Author): List<Book>
    fun findBooks(year: Year): List<Book>
    fun findBooks(genre: Genre)

    fun getAllBooks(): List<Book>
    fun getAllAvailableBooks(): List<Book>

    fun getBookStatus(book: Book): Status
    fun getAllBookStatuses(): Map<Book, Status>

    fun setBookStatus(book: Book, status: Status)

    fun addBook(book: Book, status: Status = Status.Available)

    fun registerUser(/* parameters */)
    fun unregisterUser(user: User)

    fun takeBook(user: User, book: Book)
    fun returnBook(book: Book)
}
