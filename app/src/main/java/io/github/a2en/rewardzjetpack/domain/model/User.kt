package io.github.a2en.rewardzjetpack.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    val userName: String,
    val email: String,
    val phone: String,
    @PrimaryKey(autoGenerate = true) val id: Long?
)
