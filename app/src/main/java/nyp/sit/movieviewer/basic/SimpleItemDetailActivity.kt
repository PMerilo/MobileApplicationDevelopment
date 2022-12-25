package nyp.sit.movieviewer.basic

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.moviedetailmenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.addFav) {
            val movieItem: SimpleMovieItem = SimpleMovieSampleData.simpleMovieitemArray[intent.getIntExtra("moviePos", -1)]
            val app = MovieViewerApplication.appInstance
            app.addtoDb(movieItem, applicationContext)
        }
        return super.onOptionsItemSelected(item)
    }

}
