package dao

import model.Autor
import java.io.IOException
import java.text.ParseException
import java.text.SimpleDateFormat


class AutorDao {
    private val FILENAME = "resources/autores.txt"
    private val DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd")

    @Throws(IOException::class)
    private fun generateId(): Int {
        val autores = findAll()
        return (autores.maxOfOrNull { it.id } ?: 0) + 1
    }

    @Throws(IOException::class)
    fun save(autor: Autor) {
        if (autor.id == 0) {
            autor.id = generateId()
        }
        val data = "${autor.id},${autor.nombre},${DATE_FORMAT.format(autor.fechaNacimiento)},${autor.activo}"
        FileUtil.writeToFile(FILENAME, data)
    }

    @Throws(IOException::class)
    fun findAll(): List<Autor> {
        val autores = mutableListOf<Autor>()
        val lines = FileUtil.readFromFile(FILENAME)
        for (line in lines) {
            val parts = line.split(",")
            if (parts.size < 4) continue
            try {
                val autor = Autor(
                    id = parts[0].toInt(),
                    nombre = parts[1],
                    fechaNacimiento = DATE_FORMAT.parse(parts[2]),
                    activo = parts[3].toBoolean()
                )
                autores.add(autor)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
        }
        return autores
    }

    @Throws(IOException::class)
    fun update(autor: Autor) {
        val autores = findAll().toMutableList()
        val index = autores.indexOfFirst { it.id == autor.id }
        if (index != -1) {
            autores[index] = autor
            saveAll(autores)
        }
    }

    @Throws(IOException::class)
    fun delete(id: Int) {
        val autores = findAll().toMutableList()
        autores.removeIf { it.id == id }
        saveAll(autores)
    }

    @Throws(IOException::class)
    fun findById(id: Int): Autor? {
        return findAll().find { it.id == id }
    }

    @Throws(IOException::class)
    private fun saveAll(autores: List<Autor>) {
        val data = autores.joinToString("\n") {
            "${it.id},${it.nombre},${DATE_FORMAT.format(it.fechaNacimiento)},${it.activo}"
        }
        FileUtil.writeToFile(FILENAME, data, append = false)
    }
}
