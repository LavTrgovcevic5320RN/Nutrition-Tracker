package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false)
@TypeConverters()
abstract class MovieDataBase : RoomDatabase() {
    abstract fun getMovieDao(): MovieDao
}