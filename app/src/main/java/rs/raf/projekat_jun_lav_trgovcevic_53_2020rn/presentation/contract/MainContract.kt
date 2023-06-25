package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.contract

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.Category
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.Meal
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.SavedMeal
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.states.*
import java.util.Date

interface MainContract {

    interface ViewModel {

        val categoriesState: LiveData<CategoriesState>
        val usersState: LiveData<UsersState>
        val mealsState: LiveData<MealsState>
        val saveMealState: LiveData<SaveMealState>
        val addDone: LiveData<AddUserState>
        var selectedMeal : Meal
        var selectedCateg: MutableLiveData<Category>
//        var filterKeyword: String


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

        fun getAllSavedMeals()
        fun addSavedMeal(mealToSave: SavedMeal)
        fun getAllSavedMealsByName(name: String)



//        fun getAllMealsFilterByArea()
//        fun getAllMealsByIngredient()

    }

}