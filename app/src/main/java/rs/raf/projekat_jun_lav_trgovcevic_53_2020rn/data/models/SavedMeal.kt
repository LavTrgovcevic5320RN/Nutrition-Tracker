package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models

import java.io.Serializable
import java.util.*

data class SavedMeal(
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
): Serializable