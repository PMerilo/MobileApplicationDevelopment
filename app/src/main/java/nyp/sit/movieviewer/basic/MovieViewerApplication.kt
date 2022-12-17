package nyp.sit.movieviewer.basic

import android.app.Application
import nyp.sit.movieviewer.basic.entity.SimpleMovieItem

class MovieViewerApplication(): Application(){

    companion object {
        var movieItemArrays: ArrayList<SimpleMovieItem>?=null
        val appInstance = MovieViewerApplication()
    }

}