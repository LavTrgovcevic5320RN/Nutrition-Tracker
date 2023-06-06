package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.states

sealed class AddMovieState {
    object Success: AddMovieState()
    data class Error(val message: String): AddMovieState()
}