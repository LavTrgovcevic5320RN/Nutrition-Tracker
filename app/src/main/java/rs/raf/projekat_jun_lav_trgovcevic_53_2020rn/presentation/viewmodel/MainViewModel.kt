package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.Meal
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.Resource
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.User
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.repositories.CategoryRepository
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.repositories.UserRepository
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.contract.MainContract
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.states.*
import timber.log.Timber
import java.util.concurrent.TimeUnit

class MainViewModel(
    private val categoryRepository: CategoryRepository,
    private val userRepository: UserRepository,
) : ViewModel(), MainContract.ViewModel {

    override var selectedMeal: Meal = Meal(
            "52768","Apple Frangipan Tart","null","Dessert","British","Preheat the oven to 200C/180C Fan/Gas 6.\r\nPut the biscuits in a large re-sealable freezer bag and bash with a rolling pin into fine crumbs. Melt the butter in a small pan, then add the biscuit crumbs and stir until coated with butter. Tip into the tart tin and, using the back of a spoon, press over the base and sides of the tin to give an even layer. Chill in the fridge while you make the filling.\r\nCream together the butter and sugar until light and fluffy. You can do this in a food processor if you have one. Process for 2-3 minutes. Mix in the eggs, then add the ground almonds and almond extract and blend until well combined.\r\nPeel the apples, and cut thin slices of apple. Do this at the last minute to prevent the apple going brown. Arrange the slices over the biscuit base. Spread the frangipane filling evenly on top. Level the surface and sprinkle with the flaked almonds.\r\nBake for 20-25 minutes until golden-brown and set.\r\nRemove from the oven and leave to cool for 15 minutes. Remove the sides of the tin. An easy way to do this is to stand the tin on a can of beans and push down gently on the edges of the tin.\r\nTransfer the tart, with the tin base attached, to a serving plate. Serve warm with cream, cr\u00e8me fraiche or ice cream.",
    "https://www.themealdb.com/images/media/meals/wxywrq1468235067.jpg","Tart,Baking,Fruity","https://www.youtube.com/watch?v=rp8Slv4INLk",
    "digestive biscuits","butter","Bramley apples","butter, softened","caster sugar","free-range eggs, beaten","ground almonds","almond extract","flaked almonds","","","","","","","","","","","175g/6oz","75g/3oz","200g/7oz","75g/3oz","75g/3oz","2","75g/3oz","1 tsp","50g/1\u00beoz","","","","","","","","","","","","","null","null","null","null"
    )

    private val subscriptions = CompositeDisposable()
    override val categoriesState: MutableLiveData<CategoriesState> = MutableLiveData()
    override val usersState: MutableLiveData<UsersState> = MutableLiveData()
    override val mealsState: MutableLiveData<MealsState> = MutableLiveData()
    override val addDone: MutableLiveData<AddUserState> = MutableLiveData()
    private val publishSubject: PublishSubject<String> = PublishSubject.create()

    init {
        val subscription1 = publishSubject
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

        val user = User("1", "lavina", "lavina")

        val subscription2 = userRepository
            .insert(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    addDone.value = AddUserState.Success
                },
                {
                    addDone.value = AddUserState.Error("Error happened while adding users")
                    Timber.e(it)
                }
            )

        subscriptions.add(subscription1)
        subscriptions.add(subscription2)
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

    override fun getAllUsers() {
        val subscription = userRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    usersState.value = UsersState.Success(it)
                },
                {
                    usersState.value = UsersState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getUsersByName(name: String) {
        publishSubject.onNext(name)
    }

    override fun fetchAllMeals() {
        val subscription = categoryRepository
            .fetchAllMeals()
            .startWith(Resource.Loading()) //Kada se pokrene fetch hocemo da postavimo stanje na Loading
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it) {
                        is Resource.Loading -> mealsState.value = MealsState.Loading
                        is Resource.Success -> mealsState.value = MealsState.DataFetched
                        is Resource.Error -> mealsState.value = MealsState.Error("Error happened while fetching data from the server")
                    }
                },
                {
                    mealsState.value = MealsState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllMeals() {
        val subscription = categoryRepository
            .getAllMeals()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    mealsState.value = MealsState.Success(it)
                },
                {
                    mealsState.value = MealsState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllMealsFilterByCategory(category: String){
        val subscription = categoryRepository
            .getAllMealsFilterByCategory(category)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    mealsState.value = MealsState.Success(it)
                },
                {
                    mealsState.value = MealsState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }
    override fun getAllMealsFilterByArea(area: String){
        val subscription = categoryRepository
            .getAllMealsFilterByArea(area)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    mealsState.value = MealsState.Success(it)
                },
                {
                    mealsState.value = MealsState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }
    override fun getAllMealsByIngredient(ingredient: String){
        val subscription = categoryRepository
            .getAllMealsByIngredient(ingredient)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    mealsState.value = MealsState.Success(it)
                },
                {
                    mealsState.value = MealsState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllMealsByName(name: String){
        val subscription = categoryRepository
            .getAllMealsByName(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    mealsState.value = MealsState.Success(it)
                },
                {
                    mealsState.value = MealsState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }
}