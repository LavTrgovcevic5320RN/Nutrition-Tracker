package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.datasources.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.MealEntity

@Dao
abstract class MealDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insert(entity: MealEntity): Completable

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertAll(entities: List<MealEntity>): Completable

    @Query("SELECT * FROM meals")
    abstract fun getAll(): Observable<List<MealEntity>>

    @Query("DELETE FROM meals")
    abstract fun deleteAll()

    @Transaction
    open fun deleteAndInsertAll(entities: List<MealEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }

    @Query("SELECT * FROM meals WHERE name LIKE :name || '%'")
    abstract fun getByName(name: String): Observable<List<MealEntity>>

    @Query("SELECT * FROM meals WHERE category LIKE :category || '%'")
    abstract fun getAllMealsFilterByCategory(category: String): Observable<List<MealEntity>>

    @Query("SELECT * FROM meals WHERE area LIKE :area || '%'")
    abstract fun getAllMealsFilterByArea(area: String): Observable<List<MealEntity>>

    @Query("SELECT * FROM meals WHERE ingredients LIKE '%' || :ingredient || '%'")
    abstract fun getAllMealsByIngredient(ingredient: String): Observable<List<MealEntity>>

    @Query("SELECT * FROM meals WHERE name LIKE :name || '%'")
    abstract fun getAllMealsByName(name: String): Observable<List<MealEntity>>

    @Query("SELECT * FROM meals ORDER BY name LIMIT 10 OFFSET (:page - 1) * 10")
    abstract fun getAllMealsByPage(page: String): Observable<List<MealEntity>>


}