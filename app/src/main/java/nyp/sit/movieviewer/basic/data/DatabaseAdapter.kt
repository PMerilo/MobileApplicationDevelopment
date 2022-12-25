package nyp.sit.movieviewer.basic.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.util.Log

class DatabaseAdapter(c: Context) {
    private var dbHelper: SimpleMovieDbHelper? = null
    private var _db: SQLiteDatabase? = null
    private val context: Context?= null

    init {
        dbHelper = SimpleMovieDbHelper(c)

    }

    fun close() {
        _db?.close()

    }


    fun open() {
        try {
            _db = dbHelper?.readableDatabase
        }
        catch (e: SQLiteException){
            _db = dbHelper?.readableDatabase
        }

    }

    fun insertEntry(title: String, overview: String, language:String, date:String): Long? {
        val newMovieValues = ContentValues()

        newMovieValues.put(SimpleMovieDbHelper.TITLE, title)
        newMovieValues.put(SimpleMovieDbHelper.OVERVIEW, overview)
        newMovieValues.put(SimpleMovieDbHelper.LANGUAGE, language)
        newMovieValues.put(SimpleMovieDbHelper.DATE, date)

        return _db?.insert(SimpleMovieDbHelper.DATABASE_TABLE, null, newMovieValues)
    }

    fun removeEntry(_rowIndex: Int): Boolean {

        if (_db!!.delete(SimpleMovieDbHelper.DATABASE_TABLE,
                SimpleMovieDbHelper.KEY_ID + "=" + _rowIndex,
                null)<= 0){
            Log.w(SimpleMovieDbHelper.MYDBADAPTER_LOG_CAT, "Removing entry where id =$_rowIndex failed")
            return false
        }

        return true
    }

    fun updateEntry(_rowIndex: Long, title: String, overview: String, language:String, date:String): Int? {
        val updateMovieValues = ContentValues()

        updateMovieValues.put(SimpleMovieDbHelper.TITLE, title)
        updateMovieValues.put(SimpleMovieDbHelper.OVERVIEW, overview)
        updateMovieValues.put(SimpleMovieDbHelper.LANGUAGE, language)
        updateMovieValues.put(SimpleMovieDbHelper.DATE, date)

        val where = SimpleMovieDbHelper.KEY_ID + "=" + _rowIndex

        return _db?.update(SimpleMovieDbHelper.DATABASE_TABLE, updateMovieValues, where, null)
    }

    fun retrieveAllEntriesCursor(): Cursor? {

        var c: Cursor? = null
        try {
            c = _db?.query(SimpleMovieDbHelper.DATABASE_TABLE,
                arrayOf(SimpleMovieDbHelper.KEY_ID,SimpleMovieDbHelper.TITLE,SimpleMovieDbHelper.OVERVIEW,SimpleMovieDbHelper.LANGUAGE,SimpleMovieDbHelper.DATE),
                null,
                null,
                null,
                null,
                null)
        } catch (e: SQLiteException) {
            Log.w(SimpleMovieDbHelper.DATABASE_TABLE, "retrieve fail")
        }


        return c
    }


}