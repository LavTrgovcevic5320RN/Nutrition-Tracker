package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.User

interface UserRepository {

    fun getAll(): Observable<List<User>>
    fun getAllByName(name: String): Observable<List<User>>
    fun getAllByNameAndPassword(name: String, password: String): Observable<List<User>>
    fun insert(user: User): Completable
}