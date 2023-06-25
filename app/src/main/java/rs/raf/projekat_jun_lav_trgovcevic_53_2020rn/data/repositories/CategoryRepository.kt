package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.Category
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.Meal
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.Resource
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.SavedMeal
import java.util.*

interface CategoryRepository {

    fun fetchAll(): Observable<Resource<Unit>>
    fun getAll(): Observable<List<Category>>
    fun getAllByName(name: String): Observable<List<Category>>
    fun insert(category: Category): Completable

    fun fetchAllMeals(): Observable<Resource<Unit>>
    fun getAllMeals(): Observable<List<Meal>>
    fun getAllMealsFilterByCategory(category: String): Observable<List<Meal>>
    fun getAllMealsFilterByArea(area: String): Observable<List<Meal>>
    fun getAllMealsByIngredient(ingredient: String): Observable<List<Meal>>
    fun getAllMealsByName(name: String): Observable<List<Meal>>
    fun getAllMealsByPage(page: String): Observable<List<Meal>>

    //SavedMealDao
    fun getSavedMeals(): Observable<List<SavedMeal>>
    fun insertSavedMeal(mealToSave: SavedMeal): Completable
    fun getAllSavedMealsByName(name: String): Observable<List<SavedMeal>>

}