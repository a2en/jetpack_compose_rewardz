package io.github.a2en.rewardzjetpack.data.repository
import io.github.a2en.rewardzjetpack.data.data_source.UserDao
import io.github.a2en.rewardzjetpack.domain.model.User
import io.github.a2en.rewardzjetpack.domain.repository.UserRepository

class UserRepositoryImpl(
    private val dao: UserDao
) : UserRepository {

    override suspend fun getUserByPhoneNumber(phoneNumber: String): User? {
      return dao.getUserByPhone(phoneNumber)
    }

    override suspend fun insertUser(user: User) {
        dao.insertUser(user)
    }

}