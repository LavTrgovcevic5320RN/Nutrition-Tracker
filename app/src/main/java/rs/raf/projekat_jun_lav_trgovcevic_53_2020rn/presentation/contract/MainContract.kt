package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.Category
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.Meal
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.states.AddUserState
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.states.CategoriesState
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.states.MealsState
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.states.UsersState

interface MainContract {

    interface ViewModel {

        val categoriesState: LiveData<CategoriesState>
        val usersState: LiveData<UsersState>
        val mealsState: LiveData<MealsState>
        val addDone: LiveData<AddUserState>
        var selectedMeal : Meal
        var selectedCategory: Category

        fun fetchAllCategories()
        fun getAllCategories()
        fun getCategoriesByName(name: String)

        fun getAllUsers()
        fun getUsersByName(name: String)

        fun fetchAllMeals()
        fun getAllMeals()
        fun getAllMealsFilterByCategory(category: String)
        fun getAllMealsFilterByArea(area: String)
        fun getAllMealsByIngredient(ingredient: String)
        fun getAllMealsByName(name: String)

//        fun getAllMealsFilterByArea()
//        fun getAllMealsByIngredient()

    }

}