package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.AllMoviesResponse
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.MovieResponse

interface MovieService {

    @GET("films")
    fun getAll(@Query("limit") limit: Int = 1000): Observable<List<MovieResponse>>

}