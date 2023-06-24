package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.states

import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.SavedMeal

sealed class SaveMealState {

    object DataFetched: SaveMealState()
    data class Success(val savedMeals: List<SavedMeal>): SaveMealState()
    object SuccessfullySaved: SaveMealState()
    data class Error(val message: String): SaveMealState()
}