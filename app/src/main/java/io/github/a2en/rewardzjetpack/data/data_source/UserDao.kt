package io.github.a2en.rewardzjetpack.data.data_source

import androidx.room.*
import io.github.a2en.rewardzjetpack.domain.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user WHERE phone = :phone")
    suspend fun getUserByPhone(phone: String): User?
}