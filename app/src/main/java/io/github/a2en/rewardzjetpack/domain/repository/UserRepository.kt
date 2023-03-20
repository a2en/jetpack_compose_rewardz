package io.github.a2en.rewardzjetpack.domain.repository

import io.github.a2en.rewardzjetpack.domain.model.User


interface UserRepository {

    suspend fun getUserByPhoneNumber(phoneNumber: String): User?

    suspend fun insertUser(user: User)
}