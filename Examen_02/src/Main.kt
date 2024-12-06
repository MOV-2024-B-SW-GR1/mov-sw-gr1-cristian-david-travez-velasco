package src
import controller.AutorController
import controller.LibroController
import model.Autor
import model.Libro
import java.io.IOException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


fun main() {
    val main = Main()
    main.run()
}

class Main {
    private val DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd")
    private val autorController = AutorController()
    private val libroController = LibroController()

    fun run() {
        var option: Int
        do {
            println("\nMenu Principal")
            println("1.- Gestionar Autor")
            println("2.- Gestionar Libro")
            println("3.- Relación de UNO a MUCHOS")
            println("4.- Salir")
            print("Seleccione una opción: ")
            option = leerEntero()

            when (option) {
                1 -> gestionarAutor()
                2 -> gestionarLibro()
                3 -> relacionUnoAMuchos()
                4 -> println("Saliendo...")
                else -> println("Opción no válida.")
            }
        } while (option != 4)
    }

    private fun gestionarAutor() {
        var option: Int
        do {
            println("\nMenu Autor")
            println("1.- Crear Autor (No se puede tener un libro sin un autor)")
            println("2.- Ver Autor")
            println("3.- Actualizar Autor")
            println("4.- Eliminar Autor")
            println("5.- Volver al Menu")
            print("Seleccione una opción: ")
            option = leerEntero()

            when (option) {
                1 -> crearAutor()
                2 -> listarAutores()
                3 -> actualizarAutor()
                4 -> eliminarAutor()
                5 -> println("Volviendo al Menú Principal...")
                else -> println("Opción no válida.")
            }
        } while (option != 5)
    }

    private fun gestionarLibro() {
        var option: Int
        do {
            println("\nMenu Libro")
            println("1.- Crear Libro")
            println("2.- Ver Libro")
            println("3.- Actualizar Libro")
            println("4.- Eliminar Libro")
            println("5.- Volver al Menu")
            print("Seleccione una opción: ")
            option = leerEntero()

            when (option) {
                1 -> crearLibro()
                2 -> listarLibros()
                3 -> actualizarLibro()
                4 -> eliminarLibro()
                5 -> println("Volviendo al Menú Principal...")
                else -> println("Opción no válida.")
            }
        } while (option != 5)
    }

    private fun relacionUnoAMuchos() {
        println("\nRelación de UNO a MUCHOS")
        try {
            val autores = autorController.getAutores()
            for (autor in autores) {
                println("Autor ID: ${autor.id}, Nombre: ${autor.nombre}")
                val libros = libroController.getLibrosByAutor(autor.id)
                if (libros.isEmpty()) {
                    println("Este autor no tiene libros.")
                } else {
                    println("Libros:")
                    for (libro in libros) {
                        println(" - ${libro.titulo}")
                    }
                }
                println()
            }
        } catch (e: IOException) {
            println("Error al obtener la relación: ${e.message}")
        }
    }

    private fun crearAutor() {
        try {
            print("Nombre: ")
            val nombre = readLine().orEmpty()
            print("Fecha de Nacimiento (yyyy-MM-dd): ")
            val fechaNacimiento = leerFecha()
            print("Activo (true/false): ")
            val activo = leerBooleano()

            val autor = Autor(0, nombre, fechaNacimiento, activo)
            autorController.createAutor(autor)
            println("Autor creado exitosamente.")
        } catch (e: IOException) {
            println("Error al crear el autor: ${e.message}")
        }
    }

    private fun listarAutores() {
        try {
            val autores = autorController.getAutores()
            for (autor in autores) {
                println(autor)
            }
        } catch (e: IOException) {
            println("Error al listar los autores: ${e.message}")
        }
    }

    private fun actualizarAutor() {
        try {
            print("ID del autor a actualizar: ")
            val id = leerEntero()
            print("Nuevo Nombre: ")
            val nombre = readLine().orEmpty()
            print("Nueva Fecha de Nacimiento (yyyy-MM-dd): ")
            val fechaNacimiento = leerFecha()
            print("Activo (true/false): ")
            val activo = leerBooleano()

            val autor = Autor(id, nombre, fechaNacimiento, activo)
            autorController.updateAutor(autor)
            println("Autor actualizado exitosamente.")
        } catch (e: IOException) {
            println("Error al actualizar el autor: ${e.message}")
        }
    }

    private fun eliminarAutor() {
        try {
            print("ID del autor a eliminar: ")
            val id = leerEntero()
            autorController.deleteAutor(id)
            println("Autor eliminado exitosamente.")
        } catch (e: IOException) {
            println("Error al eliminar el autor: ${e.message}")
        }
    }

    private fun crearLibro() {
        try {
            if (autorController.getAutores().isEmpty()) {
                println("Debe crear al menos un autor antes de crear un libro.")
                return
            }

            print("Título: ")
            val titulo = readLine().orEmpty()
            print("Género: ")
            val genero = readLine().orEmpty()
            print("Precio: ")
            val precio = leerDouble()
            print("ID del Autor: ")
            val autorId = leerEntero()

            val autor = autorController.getAutorById(autorId)
            if (autor == null) {
                println("Autor no encontrado.")
                return
            }

            val libro = Libro(0, titulo, genero, precio, autor)
            libroController.createLibro(libro)
            println("Libro creado exitosamente.")
        } catch (e: IOException) {
            println("Error al crear el libro: ${e.message}")
        }
    }

    private fun listarLibros() {
        try {
            val libros = libroController.getLibros()
            for (libro in libros) {
                println(libro)
            }
        } catch (e: IOException) {
            println("Error al listar los libros: ${e.message}")
        }
    }

    private fun actualizarLibro() {
        try {
            print("ID del libro a actualizar: ")
            val id = leerEntero()
            print("Nuevo Título: ")
            val titulo = readLine().orEmpty()
            print("Nuevo Género: ")
            val genero = readLine().orEmpty()
            print("Nuevo Precio: ")
            val precio = leerDouble()
            print("ID del Autor: ")
            val autorId = leerEntero()

            val autor = autorController.getAutorById(autorId)
            if (autor == null) {
                println("Autor no encontrado.")
                return
            }

            val libro = Libro(id, titulo, genero, precio, autor)
            libroController.updateLibro(libro)
            println("Libro actualizado exitosamente.")
        } catch (e: IOException) {
            println("Error al actualizar el libro: ${e.message}")
        }
    }

    private fun eliminarLibro() {
        try {
            print("ID del libro a eliminar: ")
            val id = leerEntero()

            libroController.deleteLibro(id)
            println("Libro eliminado exitosamente.")
        } catch (e: IOException) {
            println("Error al eliminar el libro: ${e.message}")
        }
    }

    // Métodos de lectura
    private fun leerEntero(): Int {
        while (true) {
            val input = readLine()
            val valor = input?.toIntOrNull()
            if (valor != null) {
                return valor
            } else {
                println("Entrada no válida. Por favor, ingrese un número entero.")
            }
        }
    }

    private fun leerDouble(): Double {
        while (true) {
            val input = readLine()
            val valor = input?.toDoubleOrNull()
            if (valor != null) {
                return valor
            } else {
                println("Entrada no válida. Por favor, ingrese un número decimal.")
            }
        }
    }

    private fun leerBooleano(): Boolean {
        while (true) {
            val input = readLine()
            val valor = input?.toBooleanStrictOrNull()
            if (valor != null) {
                return valor
            } else {
                println("Entrada no válida. Por favor, ingrese 'true' o 'false'.")
            }
        }
    }

    private fun leerFecha(): Date {
        while (true) {
            val fechaStr = readLine().orEmpty()
            try {
                return DATE_FORMAT.parse(fechaStr)
            } catch (e: ParseException) {
                println("Formato de fecha no válido. Por favor, ingrese la fecha en el formato yyyy-MM-dd.")
            }
        }
    }
}
