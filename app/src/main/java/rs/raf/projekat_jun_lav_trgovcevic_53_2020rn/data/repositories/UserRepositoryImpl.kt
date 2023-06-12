package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.datasources.local.UserDao
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.User
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.UserEntity

class UserRepositoryImpl(
    private val localDataSourceUser: UserDao,
) : UserRepository {

    override fun getAll(): Observable<List<User>> {
        return localDataSourceUser
            .getAll()
            .map {
                it.map {
                    User(it.id, it.name, it.password)
                }
            }
    }

    override fun getAllByName(name: String): Observable<List<User>> {
        return localDataSourceUser
            .getByName(name)
            .map {
                it.map {
                    User(it.id, it.name, it.password)
                }
            }
    }

    override fun getAllByNameAndPassword(name: String, password: String): Observable<List<User>> {
        return localDataSourceUser
            .getByName(name)
            .map {
                it.map {
                    User(it.id, it.name, it.password)
                }
            }
    }

    override fun insert(user: User): Completable {
        val userEntity = UserEntity(user.id, user.name, user.password)
        return localDataSourceUser
            .insert(userEntity)
    }
}