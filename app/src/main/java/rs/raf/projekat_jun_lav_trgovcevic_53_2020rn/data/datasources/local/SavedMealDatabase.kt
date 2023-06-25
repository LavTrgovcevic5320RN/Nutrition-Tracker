package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rs.raf.projekat2milica_bakic_rn342018.data.datasources.converter.DateConvertor
import rs.raf.projekat2milica_bakic_rn342018.data.datasources.converter.StringListConverter
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.MealEntity
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.SavedMealEntity

@Database(
    entities = [MealEntity::class, SavedMealEntity::class],
    version = 3,
    exportSchema = false)
@TypeConverters(StringListConverter::class, DateConvertor::class)
abstract class SavedMealDatabase :  RoomDatabase() {

    abstract fun getMealDao(): MealDao
    abstract fun getSavedMealDao(): SavedMealDao
}