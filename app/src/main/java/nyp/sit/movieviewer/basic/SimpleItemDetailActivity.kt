package nyp.sit.movieviewer.basic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.simple_activity_item_detail.*
import nyp.sit.movieviewer.basic.data.SimpleMovieSampleData
import nyp.sit.movieviewer.basic.entity.SimpleMovieItem

class SimpleItemDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.simple_activity_item_detail)

        val movieItem: SimpleMovieItem = SimpleMovieSampleData.simpleMovieitemArray[intent.getIntExtra("moviePos", -1)]
        movie_title.text = movieItem.title
        movie_overview.text = movieItem.overview
        movie_langauge.text = movieItem.original_langauge
        movie_release_date.text = movieItem.release_date
    }

}
