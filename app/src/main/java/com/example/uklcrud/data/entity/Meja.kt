package com.example.uklcrud.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Meja (
    @PrimaryKey(autoGenerate = true) var uid: Int? = null,
    @ColumnInfo(name = "nomor_meja") var nomor_meja: String?,
    @ColumnInfo(name = "kapasitas") var kapasitas: String?
)