package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.states

import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.Movie

sealed class MoviesState {
    object Loading: MoviesState()
    object DataFetched: MoviesState()
    data class Success(val movies: List<Movie>): MoviesState()
    data class Error(val message: String): MoviesState()
}