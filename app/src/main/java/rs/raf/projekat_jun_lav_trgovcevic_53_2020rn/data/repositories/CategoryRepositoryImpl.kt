package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.repositories

import android.annotation.SuppressLint
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.datasources.local.CategoryDao
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.datasources.remote.CategoryService
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.*
import timber.log.Timber

class CategoryRepositoryImpl(
    private val localDataSourceCategory: CategoryDao,
    private val remoteDataSourceCategory: CategoryService
) : CategoryRepository {

    @SuppressLint("CheckResult")
    override fun fetchAll(): Observable<Resource<Unit>> {
//        remoteDataSourceCategory
//            .getAll()
//            .doOnNext {
//                val entities = it.results.map {
//                    CategoryEntity(
//                        it.idCategory,
//                        it.strCategory,
//                        it.strCategoryThumb,
//                        it.strCategoryDescription
//                    )
//                }
//                for (entity in entities) {
//                    Timber.d("Id: ${entity.idCategory}")
//                    Timber.d("Category: ${entity.strCategory}")
//                    Timber.d("Thumbnail: ${entity.strCategoryThumb}")
//                    Timber.d("Description: ${entity.strCategoryDescription}")
//                }
//            }
        return remoteDataSourceCategory
            .getAll()
            .doOnNext {
                Timber.e("Upis u bazu")
                val entities = it.results.map {
                    CategoryEntity(
                        it.idCategory,
                        it.strCategory,
                        it.strCategoryThumb,
                        it.strCategoryDescription
                    )
                }
                localDataSourceCategory.deleteAndInsertAll(entities)
            }
            .map {
                Resource.Success(Unit)
            }
        // Kada zelimo sami da kontrolisemo sta se desava sa greskom, umesto da je samo prepustimo
        // error handleru, mozemo iskoristiti operator onErrorReturn, tada sami na osnovu greske
        // odlucujemo koju vrednost cemo da vratimo. Ta vrednost mora biti u skladu sa povratnom
        // vrednoscu lanca.
        // Kada se iskoristi onErrorReturn onError lambda u viewmodelu nece biti izvrsena,
        // nego ce umesdto nje biti izvsena onNext koja ce kao parametar primiti vrednost koja
        // je vracena iz onErrorReturn
        // Obicno se koristi kada je potrebno proveriti koji kod greske je vratio server.
//            .onErrorReturn {
//                when(it) {
//                    is HttpException -> {
//                        when(it.code()) {
//                            in 400..499 -> {
//
//                            }
//                        }
//                    }
//                }
//                Timber.e("ON ERROR RETURN")
//            }
    }

    override fun getAll(): Observable<List<Category>> {
        return localDataSourceCategory
            .getAll()
            .map {
                it.map {
                    Category(it.idCategory, it.strCategory, it.strCategoryThumb, it.strCategoryDescription)
                }
            }
    }

    override fun getAllByName(name: String): Observable<List<Category>> {
        return localDataSourceCategory
            .getByName(name)
            .map {
                it.map {
                    Category(it.idCategory, it.strCategory, it.strCategoryThumb, it.strCategoryDescription)
                }
            }
    }

    override fun insert(category: Category): Completable {
        val categoryEntity = CategoryEntity(category.id, category.name, category.image, category.description)
        return localDataSourceCategory
            .insert(categoryEntity)
    }

}