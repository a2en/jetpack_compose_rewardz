package io.github.a2en.rewardzjetpack.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.a2en.rewardzjetpack.domain.model.User

@Database(
    entities = [User::class],
    version = 1
)
abstract class UserDatabase: RoomDatabase() {

    abstract val userDao: UserDao

    companion object {
        const val DATABASE_NAME = "user_db"
    }
}