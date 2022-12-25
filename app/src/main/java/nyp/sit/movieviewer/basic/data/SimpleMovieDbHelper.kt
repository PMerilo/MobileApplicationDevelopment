package nyp.sit.movieviewer.basic.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class SimpleMovieDbHelper(c: Context) : SQLiteOpenHelper(c, DATABASE_NAME, null, DATABASE_VER) {

    companion object {
        val DATABASE_NAME = "simplemovie.db"
        val DATABASE_VER = 1
        val DATABASE_TABLE = "simplemovieDb"

        val KEY_ID = "_id"
        val COLUMN_KEY_ID = 0

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

        val MYDBADAPTER_LOG_CAT = "MY_LOG"
    }



    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(DATABASE_CREATE)
        Log.w(MYDBADAPTER_LOG_CAT, "HELPER : DB $DATABASE_TABLE created!")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE)
        onCreate(db)

    }
}