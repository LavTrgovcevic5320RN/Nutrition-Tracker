package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.datasources.local.MovieDataBase
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.datasources.remote.MovieService
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.repositories.MovieRepository
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.repositories.MovieRepositoryImpl
import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.viewmodel.MainViewModel

val movieModule = module {

    viewModel { MainViewModel(movieRepository = get()) }

    single<MovieRepository> { MovieRepositoryImpl(localDataSource = get(), remoteDataSource = get()) }

    single { get<MovieDataBase>().getMovieDao() }

    single<MovieService> { create(retrofit = get()) }

}

