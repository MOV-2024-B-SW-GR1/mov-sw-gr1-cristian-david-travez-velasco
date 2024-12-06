package dao

import model.Autor
import model.Libro
import java.io.IOException


class LibroDao {
    private val FILENAME = "resources/libros.txt"
    private val autorDao = AutorDao()

    @Throws(IOException::class)
    private fun generateId(): Int {
        val libros = findAll()
        return (libros.maxOfOrNull { it.id } ?: 0) + 1
    }

    @Throws(IOException::class)
    fun save(libro: Libro) {
        if (libro.id == 0) {
            libro.id = generateId()
        }
        val data = "${libro.id},${libro.titulo},${libro.genero},${libro.precio},${libro.autor.id}"
        FileUtil.writeToFile(FILENAME, data)
    }

    @Throws(IOException::class)
    fun findAll(): List<Libro> {
        val libros = mutableListOf<Libro>()
        val lines = FileUtil.readFromFile(FILENAME)
        for (line in lines) {
            val parts = line.split(",")
            if (parts.size < 5) continue
            val autor = autorDao.findById(parts[4].toInt())
            if (autor != null) {
                val libro = Libro(
                    id = parts[0].toInt(),
                    titulo = parts[1],
                    genero = parts[2],
                    precio = parts[3].toDouble(),
                    autor = autor
                )
                libros.add(libro)
            }
        }
        return libros
    }

    @Throws(IOException::class)
    fun update(libro: Libro) {
        val libros = findAll().toMutableList()
        val index = libros.indexOfFirst { it.id == libro.id }
        if (index != -1) {
            libros[index] = libro
            saveAll(libros)
        }
    }

    @Throws(IOException::class)
    fun delete(id: Int) {
        val libros = findAll().toMutableList()
        libros.removeIf { it.id == id }
        saveAll(libros)
    }

    @Throws(IOException::class)
    fun findById(id: Int): Libro? {
        return findAll().find { it.id == id }
    }

    @Throws(IOException::class)
    fun findByAutorId(autorId: Int): List<Libro> {
        return findAll().filter { it.autor.id == autorId }
    }

    @Throws(IOException::class)
    private fun saveAll(libros: List<Libro>) {
        val data = libros.joinToString("\n") {
            "${it.id},${it.titulo},${it.genero},${it.precio},${it.autor.id}"
        }
        FileUtil.writeToFile(FILENAME, data, append = false)
    }
}
