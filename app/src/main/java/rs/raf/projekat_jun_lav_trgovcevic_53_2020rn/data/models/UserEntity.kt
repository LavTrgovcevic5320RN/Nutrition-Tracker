package rs.raf.projekat_jun_lav_trgovcevic_53_2020rn.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val password: String,
    val loggedIn: Boolean
)

