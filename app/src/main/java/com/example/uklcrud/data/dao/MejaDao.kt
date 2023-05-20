package com.example.uklcrud.data.dao

import androidx.room.*
import com.example.uklcrud.data.entity.Meja

@Dao
interface MejaDao {
    @Query("SELECT * FROM meja")
    fun getAll(): List<Meja>

    @Query("SELECT * FROM meja WHERE uid IN (:mejaIds)")
    fun loadAllByIds(mejaIds: IntArray): List<Meja>

    @Insert
    fun insertAll(vararg mejas: Meja)

    @Delete
    fun delete(mejas: Meja)

    @Query("SELECT * FROM meja WHERE uid= :uid")
    fun get(uid: Int) : Meja

    @Update
    fun update(meja: Meja)
}