package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.repositories

import android.annotation.SuppressLint
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.datasources.local.CategoryDao
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.datasources.local.MealDao
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.datasources.local.SavedMealDao
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.datasources.remote.CategoryService
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.datasources.remote.MealService
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.*
import timber.log.Timber

class CategoryRepositoryImpl(
    private val localDataSourceCategory: CategoryDao,
    private val remoteDataSourceCategory: CategoryService,
    private val localDataSourceMeal: MealDao,
    private val remoteDataSourceMeal: MealService,
    private val localDataSourceSavedMeal: SavedMealDao,
    ) : CategoryRepository {

    @SuppressLint("CheckResult")
    override fun fetchAll(): Observable<Resource<Unit>> {
        return remoteDataSourceCategory
            .getAll()
            .doOnNext {
                Timber.e("Upis u bazu")
                val entities = it.results.map {
                    CategoryEntity(
                        it.idCategory, it.strCategory, it.strCategoryThumb, it.strCategoryDescription
                    )
                }
                localDataSourceCategory.deleteAndInsertAll(entities)
            }
            .map {
                Resource.Success(Unit)
            }
    }

    override fun getAll(): Observable<List<Category>> {
        return localDataSourceCategory
            .getAll()
            .map {
                it.map {
                    Category(it.idCategory, it.strCategory, it.strCategoryThumb, it.strCategoryDescription)
                }
            }
    }

    override fun getAllByName(name: String): Observable<List<Category>> {
        return localDataSourceCategory
            .getByName(name)
            .map {
                it.map {
                    Category(it.idCategory, it.strCategory, it.strCategoryThumb, it.strCategoryDescription)
                }
            }
    }

    override fun insert(category: Category): Completable {
        val categoryEntity = CategoryEntity(category.id, category.name, category.image, category.description)
        return localDataSourceCategory
            .insert(categoryEntity)
    }

    @SuppressLint("CheckResult")
    override fun fetchAllMeals(): Observable<Resource<Unit>> {
        val alphabet = ('a'..'z').toList().filter { it !in listOf('q', 'u', 'x', 'z') }
        val observables = alphabet.map { letter ->
            remoteDataSourceMeal
                .fetchAllMealsByFirstLetter(letter.toString())
                .doOnNext {
                    Timber.e("Upis u bazu jela")
                    val entities = it.results.map { meal ->
                        MealEntity(
                            meal.idMeal, meal.strMeal, meal.strDrinkAlternate, meal.strCategory, meal.strArea, meal.strInstructions, meal.strMealThumb, meal.strTags, meal.strYoutube,
                            listOf(meal.strIngredient1, meal.strIngredient2, meal.strIngredient3, meal.strIngredient4, meal.strIngredient5,
                                meal.strIngredient6, meal.strIngredient7, meal.strIngredient8, meal.strIngredient9, meal.strIngredient10,
                                meal.strIngredient11, meal.strIngredient12, meal.strIngredient13, meal.strIngredient14, meal.strIngredient15,
                                meal.strIngredient16, meal.strIngredient17, meal.strIngredient18, meal.strIngredient19, meal.strIngredient20) as List<String>,
                            listOf(meal.strMeasure1, meal.strMeasure2, meal.strMeasure3, meal.strMeasure4, meal.strMeasure5,
                                meal.strMeasure6, meal.strMeasure7, meal.strMeasure8, meal.strMeasure9, meal.strMeasure10,
                                meal.strMeasure11, meal.strMeasure12, meal.strMeasure13, meal.strMeasure14, meal.strMeasure15,
                                meal.strMeasure16, meal.strMeasure17, meal.strMeasure18, meal.strMeasure19, meal.strMeasure20) as List<String>,
                            meal.strSource, meal.strImageSource, meal.strCreativeCommonsConfirmed, meal.dateModified
                        )
                    }
                    localDataSourceMeal.insertAll(entities).blockingAwait()
                }
                .map {
                    Resource.Success(Unit)
                }
        }
        return Observable.concat(observables)
    }

    override fun getAllMeals(): Observable<List<Meal>> {
        return localDataSourceMeal
            .getAll()
            .map {
                it.map {
                    Meal(
                        it.id, it.name, it.drink.toString(), it.category, it.area, it.instructions, it.image, it.tags.toString(),
                        it.youtube.toString(), it.ingredients, it.measures, it.source.toString(), it.imageSource.toString(), it.creativeCommonsConfirmed.toString(), it.dateModified.toString()
                    )
                }
            }
    }

    override fun getAllMealsFilterByCategory(category: String): Observable<List<Meal>> {
        return localDataSourceMeal
            .getAllMealsFilterByCategory(category)
            .map {
                it.map {
                    Meal(
                        it.id, it.name, it.drink.toString(), it.category, it.area, it.instructions, it.image, it.tags.toString(),
                        it.youtube.toString(), it.ingredients, it.measures, it.source.toString(), it.imageSource.toString(), it.creativeCommonsConfirmed.toString(), it.dateModified.toString()
                    )
                }
            }
    }

    override fun getAllMealsFilterByArea(area: String): Observable<List<Meal>> {
        return localDataSourceMeal
            .getAllMealsFilterByArea(area)
            .map {
                it.map {
                    Meal(
                        it.id, it.name, it.drink.toString(), it.category, it.area, it.instructions, it.image, it.tags.toString(),
                        it.youtube.toString(), it.ingredients, it.measures, it.source.toString(), it.imageSource.toString(), it.creativeCommonsConfirmed.toString(), it.dateModified.toString()
                    )
                }
            }
    }

    override fun getAllMealsByIngredient(ingredient: String): Observable<List<Meal>> {
        return localDataSourceMeal
            .getAllMealsByIngredient(ingredient)
            .map {
                it.map {
                    Meal(
                        it.id, it.name, it.drink.toString(), it.category, it.area, it.instructions, it.image, it.tags.toString(),
                        it.youtube.toString(), it.ingredients, it.measures, it.source.toString(), it.imageSource.toString(), it.creativeCommonsConfirmed.toString(), it.dateModified.toString()
                    )
                }
            }
    }

    override fun getAllMealsByName(name: String): Observable<List<Meal>> {
        return localDataSourceMeal
            .getAllMealsByName(name)
            .map {
                it.map {
                    Meal(
                        it.id, it.name, it.drink.toString(), it.category, it.area, it.instructions, it.image, it.tags.toString(),
                        it.youtube.toString(), it.ingredients, it.measures, it.source.toString(), it.imageSource.toString(), it.creativeCommonsConfirmed.toString(), it.dateModified.toString()
                    )
                }
            }
    }

    override fun getAllMealsByPage(page: String): Observable<List<Meal>> {
        return localDataSourceMeal
            .getAllMealsByPage(page)
            .map {
                it.map {
                    Meal(
                        it.id, it.name, it.drink.toString(), it.category, it.area, it.instructions, it.image, it.tags.toString(),
                        it.youtube.toString(), it.ingredients, it.measures, it.source.toString(), it.imageSource.toString(), it.creativeCommonsConfirmed.toString(), it.dateModified.toString()
                    )
                }
            }
    }

    override fun getSavedMeals(): Observable<List<SavedMeal>> {
        return localDataSourceSavedMeal
            .getAll()
            .map {
                it.map {
                    SavedMeal(it.id, it.name, it.image, it.instructions, it.youtube, it.ingredients, it.measures, it.category, it.date, it.type)
                }
            }
    }

    override fun insertSavedMeal(mealToSave: SavedMeal): Completable {
        val savedRecipeEntity = SavedMealEntity(mealToSave.id, mealToSave.name, mealToSave.image, mealToSave.instructions, mealToSave.youtube, mealToSave.ingredients, mealToSave.measures, mealToSave.category, mealToSave.date, mealToSave.type)
        return localDataSourceSavedMeal
            .insert(savedRecipeEntity)
    }

    override fun getAllSavedMealsByName(name: String): Observable<List<SavedMeal>> {
        return localDataSourceSavedMeal
            .getAllByName(name)
            .map {
                it.map {
                    SavedMeal(it.id, it.name, it.image, it.instructions, it.youtube, it.ingredients, it.measures, it.category, it.date, it.type)
                }
            }
    }

}