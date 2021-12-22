import java.util.*

typealias Year = Int

data class Book(
    val title: String,
    val isbn: String = "No isbn",
    val author: Author,
    val year: Year = 0,
    val genre: Genre,
    val description: String = "",
    val registrationDate: Date = Date(),
) {
    override fun toString(): String {
        return """
            |title $title
            |isbn $isbn
            |author $author
            |year $year
            |genre $genre
        }
        |""".trimMargin()
    }
}

data class Author(
    val firstName: String = "",
    val middleName: String = "",
    val lastName: String = "",
) {
    override fun toString(): String {
        return "$firstName $middleName $lastName"
    }
}

data class User(
    val firstName: String,
    val middleName: String,
    val lastName: String,
    val registrationDate: Date = Date(),
)

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
    fun findBooks(
        title: String = "",
        isbn: String = "",
        author: Author = Author(),
        year: Year = 0,
        genre: Genre? = null,
    ): List<Book>

    fun getAllBooks(): List<Book>
    fun getAllAvailableBooks(): List<Book>

    fun getBookStatus(book: Book): Status
    fun getAllBookStatuses(): Map<Book, Status>

    fun setBookStatus(
        book: Book,
        status: Status,
    )

    fun addBook(book: Book)

    fun registerUser(
        firstName: String,
        middleName: String,
        lastName: String,
    )

    fun unregisterUser(user: User)

    fun takeBook(user: User, book: Book)
    fun returnBook(book: Book)
}

class LibraryServiceImpl : LibraryService {
    private val userList = mutableListOf<User>()
    private val mapOfBook = mutableMapOf<Book, Status>()

    override fun findBooks(
        title: String,
        isbn: String,
        author: Author,
        year: Year,
        genre: Genre?,
    ): List<Book> {
        return mapOfBook.keys.filter { elem ->
            ((title == "") || (title == elem.title))
                    && ((isbn == "") || (isbn == elem.isbn))
                    && ((author == Author()) || (author == elem.author))
                    && ((year == 0) || (year == elem.year))
                    && ((genre == null) || (genre == elem.genre))
        }
    }

    override fun getAllBooks(): List<Book> {
        val result = mutableListOf<Book>()
        mapOfBook.forEach { result.add(it.key) }
        return result
    }

    override fun getAllAvailableBooks(): List<Book> {
        val result = mutableListOf<Book>()
        for (i in mapOfBook)
            if (i.value == Status.Available)
                result.add(i.key)
        return result
    }

    override fun getBookStatus(book: Book): Status {
        return mapOfBook[book]!!
    }

    override fun getAllBookStatuses(): Map<Book, Status> {
        return mapOfBook
    }

    override fun setBookStatus(book: Book, status: Status) {
        mapOfBook[book] = status
    }

    override fun addBook(book: Book) {
        mapOfBook[book] = Status.Available
    }

    override fun registerUser(
        firstName: String,
        middleName: String,
        lastName: String,
    ) {
        userList.add(User(firstName, middleName, lastName))
    }

    override fun unregisterUser(user: User) {
        userList.remove(user)
    }

    override fun takeBook(user: User, book: Book) {
        try {
            userList.find { it == user }
        } catch (e: Exception) {
            throw IllegalArgumentException("User not found")
        }
        if (mapOfBook.values.count { ((it as Status.UsedBy).user) == user } > 3)
            throw IllegalArgumentException("User can't have more than 3 books")
        else setBookStatus(book, Status.UsedBy(user))
    }

    override fun returnBook(book: Book) {
        setBookStatus(book, Status.Available)
    }

}