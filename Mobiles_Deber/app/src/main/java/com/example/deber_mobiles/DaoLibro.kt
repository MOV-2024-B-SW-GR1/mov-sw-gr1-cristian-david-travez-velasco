package com.example.crud_room_kotlin

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DaoLibro {
    @Query("SELECT * FROM libros")
    suspend fun obtenerLibros(): MutableList<Libro>

    @Insert
    suspend fun agregarLibro(libro: Libro)

    @Query("UPDATE libros set autor=:autor WHERE titulo=:titulo")
    suspend fun actualizarLibro(titulo: String, autor: String)

    @Query("DELETE FROM libros WHERE titulo=:titulo")
    suspend fun borrarLibro(titulo: String)
}