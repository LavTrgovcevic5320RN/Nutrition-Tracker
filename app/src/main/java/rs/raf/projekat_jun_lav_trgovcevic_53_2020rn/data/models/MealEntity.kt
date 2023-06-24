package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meals")
class MealEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val drink: String?,
    val category: String,
    val area: String,
    val instructions: String,
    val image: String,
    val tags: String?,
    val youtube: String?,
    val ingredients : List<String>,
    val measures : List<String>,
    val source: String?,
    val imageSource: String?,
    val creativeCommonsConfirmed: String?,
    val dateModified: String?
)