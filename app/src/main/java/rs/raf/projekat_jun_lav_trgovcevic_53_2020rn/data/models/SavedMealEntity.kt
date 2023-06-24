package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "saved_meals")
data class SavedMealEntity(

    @PrimaryKey
    val id : String,
    val name: String,
    val image: String,
    val instructions: String,
    val youtube: String,
    val ingredients : List<String>,
    val measures : List<String>,
    val category: String,
    val date : Date,
    val type: String
)