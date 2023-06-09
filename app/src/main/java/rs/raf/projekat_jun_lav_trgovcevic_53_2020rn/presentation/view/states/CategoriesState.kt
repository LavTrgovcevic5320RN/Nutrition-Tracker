package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.states

import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.Category

sealed class CategoriesState {
    object Loading: CategoriesState()
    object DataFetched: CategoriesState()
    data class Success(val categories: List<Category>): CategoriesState()
    data class Error(val message: String): CategoriesState()
}