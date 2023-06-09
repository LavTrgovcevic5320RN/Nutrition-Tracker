package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.Movie
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.states.AddMovieState
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.states.CategoriesState

interface MainContract {

    interface ViewModel {

        val categoriesState: LiveData<CategoriesState>
//        val addDone: LiveData<AddMovieState>

        fun fetchAllCategories()
        fun getAllCategories()
        fun getCategoriesByName(name: String)
//        fun addCategorie(movie: Movie)
    }

}