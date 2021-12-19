import java.util.*

typealias Year = Int

data class Book(
    val title: String,
    val isbn: String = "No isbn",
    val author: Author,
    val year: Year = 1900,
    val genre: Genre,
    val description: String = "No description",
    val registrationDate: Calendar? = Calendar.getInstance(),
    var status: Status
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Book) return false

        if (title != other.title) return false
        if (isbn != other.isbn) return false
        if (author != other.author) return false
        if (year != other.year) return false
        if (genre != other.genre) return false
        if (description != other.description) return false
        if (registrationDate != other.registrationDate) return false
        if (status != other.status) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + isbn.hashCode()
        result = 31 * result + author.hashCode()
        result = 31 * result + year
        result = 31 * result + genre.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + (registrationDate?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return """
            |title $title
            |isbn $isbn
            |author $author
            |year $year
            |genre $genre
            |status ${when(status)
        {
            Status.Available -> "available"
            Status.ComingSoon -> "coming soon"
            Status.Restoration -> "restoration"
            is Status.UsedBy -> "used by"
        }}
        |""".trimMargin()
    }
}

data class Author(
    val firstName: String = "",
    val middleName: String = "",
    val lastName: String = ""
) {
    override fun toString(): String {
        return "$firstName $middleName $lastName"
    }
}

class User(
    val firstName: String,
    val middleName: String,
    val lastName: String,
)
{
    val registrationDate: Calendar? = Calendar.getInstance()
    val countOfBooks: Int
        get() {
            return rentedBooks.size
        }

    private val rentedBooks = mutableListOf<Book>()

    fun addBook(book: Book)
    {
        rentedBooks.add(book)
    }

    fun removeBook(book: Book)
    {
        rentedBooks.remove(book)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is User) return false

        if (firstName != other.firstName) return false
        if (middleName != other.middleName) return false
        if (lastName != other.lastName) return false
        if (registrationDate != other.registrationDate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = firstName.hashCode()
        result = 31 * result + middleName.hashCode()
        result = 31 * result + lastName.hashCode()
        result = 31 * result + (registrationDate?.hashCode() ?: 0)
        return result
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
    Educational,
    Null
}

sealed class Status {
    object Available : Status()
    data class UsedBy(val user: User) : Status()
    object ComingSoon : Status()
    object Restoration : Status()
}

interface LibraryService {
    fun findBooks(title: String = "", isbn: String = "", author: Author = Author(), year: Year = 0, genre: Genre = Genre.Null): List<Book>

    fun getAllBooks(): List<Book>
    fun getAllAvailableBooks(): List<Book>

    fun getBookStatus(book: Book): Status
    fun getAllBookStatuses(): Map<Book, Status>

    fun setBookStatus(book: Book, status: Status)

    fun addBook(book: Book)

    fun registerUser(firstName: String, middleName: String, lastName: String)
    fun unregisterUser(user: User)

    fun takeBook(user: User, book: Book)
    fun returnBook(book: Book)
}

class LibraryServiceImpl: LibraryService{
    private val bookList = mutableListOf<Book>()
    private val userList = mutableListOf<User>()
    private val disabledUser = mutableListOf<User>()

    override fun findBooks(
        title: String,
        isbn: String,
        author: Author,
        year: Year,
        genre: Genre
    ): List<Book>
    {
        return bookList.filter { elem ->
            ((title == "") || (title == elem.title))
                    && ((isbn == "") || (isbn == elem.isbn))
                    && ((author == Author()) || (author == elem.author))
                    && ((year == 0) || (year == elem.year))
                    && ((genre == Genre.Null) || (genre == elem.genre))
        }
    }


    override fun getAllBooks(): List<Book> {
        return bookList
    }

    override fun getAllAvailableBooks(): List<Book> {
        val result = mutableListOf<Book>()
        for(i in bookList)
            if(i.status is Status.Available)
                result+=i
        return result
    }

    override fun getBookStatus(book: Book): Status {
        return book.status
    }

    override fun getAllBookStatuses(): Map<Book, Status> {
        val map = mutableMapOf<Book, Status>()
        for(i in bookList)
            map.put(i, i.status)
        return map
    }

    override fun setBookStatus(book: Book, status: Status) {
        book.status = status
    }

    override fun addBook(book: Book) {
        bookList.add(book)
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
        disabledUser.add(user)
    }

    override fun takeBook(user: User, book: Book) {
        try{
            userList.find { it==user }
        }
        catch(e: Exception)
        {
            throw IllegalArgumentException("User not found")
        }
        if(user.countOfBooks<3) {
            userList.first {
                it == user
            }.addBook(book)
            book.status = Status.UsedBy(user)
        } else throw IllegalArgumentException("The user cannot have more than 3 books ")
    }

    override fun returnBook(book: Book) {
        (book.status as Status.UsedBy).user.removeBook(book)
        book.status = Status.Available
    }

}