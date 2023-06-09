package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.Category
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.Movie
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.Resource
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.repositories.CategoryRepository
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.repositories.MovieRepository
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.contract.MainContract
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.states.AddMovieState
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.states.CategoriesState
import timber.log.Timber
import java.util.concurrent.TimeUnit

class MainViewModel(
    private val categoryRepository: CategoryRepository,
) : ViewModel(), MainContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override val categoriesState: MutableLiveData<CategoriesState> = MutableLiveData()
//    override val addDone: MutableLiveData<AddMovieState> = MutableLiveData()

    private val publishSubject: PublishSubject<String> = PublishSubject.create()

    init {
        val subscription = publishSubject
            .debounce(200, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                categoryRepository
                    .getAllByName(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        Timber.e("Error in publish subject")
                        Timber.e(it)
                    }
            }
            .subscribe(
                {
                    categoriesState.value = CategoriesState.Success(it)
                },
                {
                    categoriesState.value = CategoriesState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun fetchAllCategories() {
        val subscription = categoryRepository
            .fetchAll()
            .startWith(Resource.Loading()) //Kada se pokrene fetch hocemo da postavimo stanje na Loading
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it) {
                        is Resource.Loading -> categoriesState.value = CategoriesState.Loading
                        is Resource.Success -> categoriesState.value = CategoriesState.DataFetched
                        is Resource.Error -> categoriesState.value = CategoriesState.Error("Error happened while fetching data from the server")
                    }
                },
                {
                    categoriesState.value = CategoriesState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllCategories() {
        val subscription = categoryRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    categoriesState.value = CategoriesState.Success(it)
                },
                {
                    categoriesState.value = CategoriesState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getCategoriesByName(name: String) {
        publishSubject.onNext(name)
    }

//    override fun addCategorie(category: Category) {
//        val subscription = categoryRepository
//            .insert(category)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                {
//                    addDone.value = AddMovieState.Success
//                },
//                {
//                    addDone.value = AddMovieState.Error("Error happened while adding movie")
//                    Timber.e(it)
//                }
//            )
//        subscriptions.add(subscription)
//    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }
}