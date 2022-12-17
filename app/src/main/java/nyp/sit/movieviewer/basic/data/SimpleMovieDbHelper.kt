package nyp.sit.movieviewer.basic.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SimpleMovieDbHelper(c: Context) : SQLiteOpenHelper(c, DATABASE_NAME, null, DATABASE_VER) {

    companion object {
        val DATABASE_NAME = "simplemovie.db"
        val DATABASE_VER = 1;
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(DATABASE_CREATE)
        Log.w(MYDBADAPTER_LOG_CAT, "HELPER : DB $DATABASE_TABLE created!")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE);
        onCreate(db)

    }
}