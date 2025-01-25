package java.com.ejemplo.controller;

import java.com.ejemplo.model.Libro;
import java.com.ejemplo.service.LibroService;

import java.io.IOException;
import java.util.List;

public class LibroController {
    private LibroService libroService = new LibroService();

    // Crear un nuevo libro
    public void createLibro(Libro libro) throws IOException {
        libroService.saveLibro(libro);
    }

    // Obtener todos los libros
    public List<Libro> getLibros() throws IOException {
        return libroService.getAllLibros();
    }

    // Actualizar un libro existente
    public void updateLibro(Libro libro) throws IOException {
        libroService.updateLibro(libro);
    }

    // Eliminar un libro por ID
    public void deleteLibro(int id) throws IOException {
        libroService.deleteLibro(id);
    }

    // Obtener un libro por ID
    public Libro getLibroById(int id) throws IOException {
        return libroService.getLibroById(id);
    }
}