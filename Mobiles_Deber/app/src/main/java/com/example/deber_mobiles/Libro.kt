package com.example.crud_room_kotlin

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "libros")
data class Libro(
    @PrimaryKey var titulo: String,
    @ColumnInfo(name = "autor") var autor: String
)