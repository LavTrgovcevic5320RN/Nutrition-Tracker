package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String
)