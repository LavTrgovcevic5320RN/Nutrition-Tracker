package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.SavedMealEntity
import java.util.Date

@Dao
abstract class SavedMealDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insert(entity: SavedMealEntity): Completable

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertAll(entities: List<SavedMealEntity>): Completable

    @Query("SELECT * FROM saved_meals")
    abstract fun getAll(): Observable<List<SavedMealEntity>>

    @Query("DELETE FROM saved_meals")
    abstract fun deleteAll()

    @Transaction
    open fun deleteAndInsertAll(entities: List<SavedMealEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }

    @Query("SELECT * FROM saved_meals WHERE name LIKE :name || '%'")
    abstract fun getAllByName(name: String): Observable<List<SavedMealEntity>>

//    @Query("SELECT date, COUNT(*) as count FROM saved_meals WHERE date >= :aWeekAgo AND date <= :today GROUP BY date")
//    abstract fun getSavedMealCounts(aWeekAgo: Date, today: Date): Observable<List<SavedMealEntity>>


}