package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 4,
    exportSchema = false)
@TypeConverters()
abstract class UserDataBase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
}