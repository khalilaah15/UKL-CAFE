package com.example.uklcrud.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.uklcrud.data.dao.UserDao
import com.example.uklcrud.data.entity.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    companion object {
        private var instance: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase {
            if (instance == null){
                instance = Room.databaseBuilder(context, UserDatabase::class.java, "user-database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }

            return instance!!
        }
    }
}