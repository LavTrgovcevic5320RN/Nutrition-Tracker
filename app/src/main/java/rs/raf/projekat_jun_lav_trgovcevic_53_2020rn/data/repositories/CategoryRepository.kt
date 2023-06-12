package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.Category
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.Meal
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.Resource

interface CategoryRepository {

    fun fetchAll(): Observable<Resource<Unit>>
    fun getAll(): Observable<List<Category>>
    fun getAllByName(name: String): Observable<List<Category>>
    fun insert(category: Category): Completable

    fun fetchAllMeals(): Observable<Resource<Unit>>
    fun getAllMeals(): Observable<List<Meal>>
}