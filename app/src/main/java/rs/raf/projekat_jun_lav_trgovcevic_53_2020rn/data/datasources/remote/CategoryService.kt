package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.CategoryResponseList

interface CategoryService {

    @GET("categories.php")
    fun getAll(@Query("limit") limit: Int = 1000): Observable<CategoryResponseList>
}