package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.CategoryEntity

@Database(
    entities = [CategoryEntity::class],
    version = 2,
    exportSchema = false)
@TypeConverters()
abstract class CategoryDataBase : RoomDatabase() {
    abstract fun getCategoryDao(): CategoryDao
}