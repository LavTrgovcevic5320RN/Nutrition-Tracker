package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.states

import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.Meal

sealed class MealsState {
    object Loading: MealsState()
    object DataFetched: MealsState()
    data class Success(val meals: List<Meal>): MealsState()
    data class Error(val message: String): MealsState()
}