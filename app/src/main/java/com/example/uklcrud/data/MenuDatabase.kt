package com.example.uklcrud.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.uklcrud.data.dao.MenuDao
import com.example.uklcrud.data.entity.Menu

@Database(entities = [Menu::class], version = 2)
abstract class MenuDatabase : RoomDatabase() {
    abstract fun MenuDao(): MenuDao
    companion object {
        private var instance: MenuDatabase? = null

        fun getInstance(context: Context): MenuDatabase {
            if (instance == null){
                instance = Room.databaseBuilder(context, MenuDatabase::class.java, "menu-database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }

            return instance!!
        }
    }
}