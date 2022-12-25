package nyp.sit.movieviewer.basic

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_view_list_of_movies.*
import nyp.sit.movieviewer.basic.data.SimpleMovieSampleData
import nyp.sit.movieviewer.basic.entity.SimpleMovieItem

class FavMoviesActivity : AppCompatActivity() {
    private var app = MovieViewerApplication.appInstance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_list_of_movies)
        val movieNameList = app.retrieveAll(applicationContext).mapTo(arrayListOf()) { it.title }
        Log.i("Test", movieNameList.toString())
        movielist.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, movieNameList)

        movielist.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                detailIntent(position)
            }
        }
    }

    fun detailIntent(moviePos: Int) {
        val detailIntent = Intent(this, SimpleItemDetailActivity::class.java)
        detailIntent.putExtra("moviePos", moviePos)
        startActivity(detailIntent)
    }

}