package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.CategoryEntity

@Dao
abstract class CategoryDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insert(entity: CategoryEntity): Completable

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertAll(entities: List<CategoryEntity>): Completable

    @Query("SELECT * FROM categories")
    abstract fun getAll(): Observable<List<CategoryEntity>>

    @Query("DELETE FROM categories")
    abstract fun deleteAll()

    @Transaction
    open fun deleteAndInsertAll(entities: List<CategoryEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }

    @Query("SELECT * FROM categories WHERE strCategory LIKE :name || '%'")
    abstract fun getByName(name: String): Observable<List<CategoryEntity>>

}