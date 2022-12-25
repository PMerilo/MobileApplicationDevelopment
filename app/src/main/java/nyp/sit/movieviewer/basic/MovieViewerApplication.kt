package nyp.sit.movieviewer.basic

import android.app.Application
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import nyp.sit.movieviewer.basic.data.DatabaseAdapter
import nyp.sit.movieviewer.basic.data.SimpleMovieDbHelper
import nyp.sit.movieviewer.basic.entity.SimpleMovieItem

class MovieViewerApplication(): Application(){

    companion object {
        var movieItemArrays: ArrayList<SimpleMovieItem> = ArrayList<SimpleMovieItem>()
        val appInstance = MovieViewerApplication()
    }

    fun addtoDb(movieItem: SimpleMovieItem, context: Context): Long? {
        val _db = DatabaseAdapter(context)
        val title = movieItem.title.toString()
        val overview = movieItem.overview.toString()
        val language = movieItem.original_langauge.toString()
        val date = movieItem.release_date.toString()

        _db.open()
        val rowId = _db.insertEntry(title, overview, language, date)
        _db.close()

        return rowId
    }

    fun retrieveAll(c: Context): ArrayList<SimpleMovieItem> {

        val myCursor: Cursor?
        val _db = DatabaseAdapter(c)
        _db.open()
        movieItemArrays.clear()
        myCursor = _db.retrieveAllEntriesCursor()
        if (myCursor != null && myCursor!!.count > 0 ) {
            myCursor!!.moveToFirst()
            do {
                val title = myCursor.getString(SimpleMovieDbHelper.COLUMN_TITLE_ID)
                val overview = myCursor.getString(SimpleMovieDbHelper.COLUMN_DESC_ID)
                val language = myCursor.getString(SimpleMovieDbHelper.COLUMN_LANGUAGE_ID)
                val date = myCursor.getString(SimpleMovieDbHelper.COLUMN_DATE_ID)
                movieItemArrays.add(SimpleMovieItem(overview, language, date, title))
            } while (myCursor.moveToNext())
        }
        _db.close()


        return movieItemArrays

    }

}