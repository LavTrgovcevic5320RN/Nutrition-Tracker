package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.MealResponseList

interface MealService {

//    @GET("filter.php")
//    fun fetchAllMealsByCategoryName(@Query("c") name: String): Observable<MealResponseList>

    @GET("search.php")
    fun fetchAllMealsByFirstLetter(@Query("f") firstLetter: String, @Query("limit") limit: Int = 1000): Observable<MealResponseList>
}