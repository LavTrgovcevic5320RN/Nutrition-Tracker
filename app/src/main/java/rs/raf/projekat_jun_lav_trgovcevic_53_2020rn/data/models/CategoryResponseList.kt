package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoryResponseList(
    @Json(name = "categories")
    val results: List<CategoryResponse>
)