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

    @Query("SELECT * FROM meals WHERE ingredient1 LIKE :ingredient || '%' OR ingredient2 LIKE :ingredient || '%' OR ingredient3 LIKE :ingredient || '%' OR ingredient4 LIKE :ingredient || '%' OR ingredient5 LIKE :ingredient || '%' OR ingredient6 LIKE :ingredient || '%' OR ingredient7 LIKE :ingredient || '%' OR ingredient8 LIKE :ingredient || '%' OR ingredient9 LIKE :ingredient || '%' OR ingredient10 LIKE :ingredient || '%' OR ingredient11 LIKE :ingredient || '%' OR ingredient12 LIKE :ingredient || '%' OR ingredient13 LIKE :ingredient || '%' OR ingredient14 LIKE :ingredient || '%' OR ingredient15 LIKE :ingredient || '%' OR ingredient16 LIKE :ingredient || '%' OR ingredient17 LIKE :ingredient || '%' OR ingredient18 LIKE :ingredient || '%' OR ingredient19 LIKE :ingredient || '%'  OR ingredient20 LIKE :ingredient || '%'")
    abstract fun getAllMealsByIngredient(ingredient: String): Observable<List<MealEntity>>


    @Query("SELECT * FROM meals WHERE name LIKE :name || '%'")
    abstract fun getAllMealsByName(name: String): Observable<List<MealEntity>>
}