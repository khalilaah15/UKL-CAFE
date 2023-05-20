package com.example.uklcrud.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.uklcrud.data.dao.MejaDao
import com.example.uklcrud.data.entity.Meja

@Database(entities = [Meja::class], version = 3)
abstract class MejaDatabase : RoomDatabase() {
    abstract fun MejaDao(): MejaDao
    companion object {
        private var instance: MejaDatabase? = null

        fun getInstance(context: Context): MejaDatabase {
            if (instance == null){
                instance = Room.databaseBuilder(context, MejaDatabase::class.java, "meja-database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }

            return instance!!
        }
    }
}