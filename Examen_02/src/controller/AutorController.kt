package controller

import dao.AutorDao
import model.Autor
import java.io.IOException


class AutorController {
    private val autorDao = AutorDao()

    @Throws(IOException::class)
    fun createAutor(autor: Autor) {
        autorDao.save(autor)
    }

    @Throws(IOException::class)
    fun getAutores(): List<Autor> {
        return autorDao.findAll()
    }

    @Throws(IOException::class)
    fun updateAutor(autor: Autor) {
        autorDao.update(autor)
    }

    @Throws(IOException::class)
    fun deleteAutor(id: Int) {
        autorDao.delete(id)
    }

    @Throws(IOException::class)
    fun getAutorById(id: Int): Autor? {
        return autorDao.findById(id)
    }
}
