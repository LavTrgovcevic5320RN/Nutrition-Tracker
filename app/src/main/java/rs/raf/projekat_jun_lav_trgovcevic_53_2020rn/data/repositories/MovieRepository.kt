package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.Movie
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.Resource

interface MovieRepository {

    fun fetchAll(): Observable<Resource<Unit>>
    fun getAll(): Observable<List<Movie>>
    fun getAllByName(name: String): Observable<List<Movie>>
    fun insert(movie: Movie): Completable

}