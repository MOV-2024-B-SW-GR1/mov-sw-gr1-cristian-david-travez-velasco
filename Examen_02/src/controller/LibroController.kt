package controller

import dao.LibroDao
import model.Libro
import java.io.IOException


class LibroController {
    private val libroDao = LibroDao()

    @Throws(IOException::class)
    fun createLibro(libro: Libro) {
        libroDao.save(libro)
    }

    @Throws(IOException::class)
    fun getLibros(): List<Libro> {
        return libroDao.findAll()
    }

    @Throws(IOException::class)
    fun updateLibro(libro: Libro) {
        libroDao.update(libro)
    }

    @Throws(IOException::class)
    fun deleteLibro(id: Int) {
        libroDao.delete(id)
    }

    @Throws(IOException::class)
    fun getLibroById(id: Int): Libro? {
        return libroDao.findById(id)
    }

    @Throws(IOException::class)
    fun getLibrosByAutor(autorId: Int): List<Libro> {
        return libroDao.findByAutorId(autorId)
    }
}
