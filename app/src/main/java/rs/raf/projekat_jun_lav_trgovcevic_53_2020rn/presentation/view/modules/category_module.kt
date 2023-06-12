package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.datasources.local.CategoryDataBase
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.datasources.local.MealDataBase
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.datasources.local.UserDataBase
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.datasources.remote.CategoryService
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.datasources.remote.MealService
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.repositories.CategoryRepository
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.repositories.CategoryRepositoryImpl
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.repositories.UserRepository
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.repositories.UserRepositoryImpl
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.viewmodel.MainViewModel

val category_module = module {

    viewModel { MainViewModel(categoryRepository = get(), userRepository = get()) }

    single<CategoryRepository> { CategoryRepositoryImpl(localDataSourceCategory = get(),
        remoteDataSourceCategory = get(),
        localDataSourceMeal = get(),
        remoteDataSourceMeal = get()) }

    single { get<CategoryDataBase>().getCategoryDao() }

    single<CategoryService> { create(retrofit = get()) }

    single { get<MealDataBase>().getMealDao() }

    single<MealService> { create(retrofit = get()) }

    single<UserRepository> { UserRepositoryImpl(localDataSourceUser = get()) }

    single { get<UserDataBase>().getUserDao() }

}