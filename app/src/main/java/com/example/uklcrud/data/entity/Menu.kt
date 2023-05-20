package com.example.uklcrud.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Menu (
    @PrimaryKey(autoGenerate = true) var uid: Int? = null,
    @ColumnInfo(name = "menu_name") var menu_name: String?,
    @ColumnInfo(name = "deskripsi") var deskripsi: String?,
    @ColumnInfo(name = "harga") var harga: String?,
    @ColumnInfo(name = "jenis") var jenis: String?
)