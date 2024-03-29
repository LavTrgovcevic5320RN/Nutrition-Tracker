package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rs.raf.projekat2milica_bakic_rn342018.data.datasources.converter.StringListConverter
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.MealEntity

@Database(
    entities = [MealEntity::class],
    version = 12,
    exportSchema = false)
@TypeConverters(StringListConverter::class)
abstract class MealDataBase : RoomDatabase() {
    abstract fun getMealDao(): MealDao
}