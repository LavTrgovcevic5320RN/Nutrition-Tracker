package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.presentation.view.states

import rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models.User

sealed class UsersState {
    object Loading: UsersState()
    object DataFetched: UsersState()
    data class Success(val users: List<User>): UsersState()
    data class Error(val message: String): UsersState()
}

