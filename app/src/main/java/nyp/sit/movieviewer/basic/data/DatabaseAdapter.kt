package nyp.sit.movieviewer.basic.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.util.Log

class DatabaseAdapter(c: Context) {
    private val DATABASE_TABLE = "simplemovieDb"
    private var _db: SQLiteDatabase? = null
    private val context: Context?= null

    val KEY_ID = "_id" // column names
    val COLUMN_KEY_ID = 0 // column id

    val TITLE = "title"
    val COLUMN_TITLE_ID = 1

    val OVERVIEW = "overview"
    val COLUMN_DESC_ID = 2

    val LANGUAGE = "original_language"
    val COLUMN_LANGUAGE_ID = 3

    val DATE = "release_date"
    val COLUMN_DATE_ID = 4

    protected val DATABASE_CREATE = ("create table " + DATABASE_TABLE + " " + "(" + KEY_ID + " integer primary key autoincrement, " +
            TITLE + " Text, " +
            OVERVIEW + " Text, " +
            LANGUAGE + " Text, " +
            DATE + " Text);")

    private val MYDBADAPTER_LOG_CAT = "MY_LOG"

    private var dbHelper: SimpleMovieDbHelper? = null

    init {
        dbHelper = SimpleMovieDbHelper(c)

    }

    fun close() { // closes table
        _db?.close()

    }


    fun open() { //open db
        try {
            _db = dbHelper?.readableDatabase
        }
        catch (e: SQLiteException){
            _db = dbHelper?.readableDatabase
        }

    }

    fun insertEntry(title: String, overview: String, language:String, date:String): Long? {
        val newMovieValues = ContentValues()

        newMovieValues.put(TITLE, title)
        newMovieValues.put(OVERVIEW, overview)
        newMovieValues.put(LANGUAGE, language)
        newMovieValues.put(DATE, date)

        return _db?.insert(DATABASE_TABLE, null, newMovieValues)
    }

    fun removeEntry(_rowIndex: Int): Boolean {

        if (_db!!.delete(DATABASE_TABLE,
                KEY_ID + "=" + _rowIndex,
                null)<= 0){
            Log.w(MYDBADAPTER_LOG_CAT, "Removing entry where id =$_rowIndex failed")
            return false
        }

        return true
    }

    fun updateEntry(_rowIndex: Long, title: String, overview: String, language:String, date:String): Int? {
        val updateMovieValues = ContentValues()

        updateMovieValues.put(TITLE, title)
        updateMovieValues.put(OVERVIEW, overview)
        updateMovieValues.put(LANGUAGE, language)
        updateMovieValues.put(DATE, date)

        val where = KEY_ID + "=" + _rowIndex

        return _db?.update(DATABASE_TABLE, updateMovieValues, where, null)
    }


}