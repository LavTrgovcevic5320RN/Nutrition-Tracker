package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.Movie
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.states.AddMovieState
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.states.MoviesState

interface MainContract {

    interface ViewModel {

        val moviesState: LiveData<MoviesState>
        val addDone: LiveData<AddMovieState>

        fun fetchAllMovies()
        fun getAllMovies()
        fun getMoviesByName(name: String)
        fun addMovie(movie: Movie)
    }

}