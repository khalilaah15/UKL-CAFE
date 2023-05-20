package com.example.uklcrud.data.dao

import androidx.room.*
import com.example.uklcrud.data.entity.Menu

@Dao
interface MenuDao {
    @Query("SELECT * FROM menu")
    fun getAll(): List<Menu>

    @Query("SELECT * FROM menu WHERE uid IN (:menuIds)")
    fun loadAllByIds(menuIds: IntArray): List<Menu>

    @Insert
    fun insertAll(vararg menus: Menu)

    @Delete
    fun delete(menus: Menu)

    @Query("SELECT * FROM menu WHERE uid= :uid")
    fun get(uid: Int) : Menu

    @Update
    fun update(menu: Menu)

    @Query("SELECT * FROM menu WHERE jenis = :jenisMenu")
    fun getMenuFilterJenis(jenisMenu: String): List<Menu>
}