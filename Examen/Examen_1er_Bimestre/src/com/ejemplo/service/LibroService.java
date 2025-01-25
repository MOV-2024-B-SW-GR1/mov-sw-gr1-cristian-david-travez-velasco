package java.com.ejemplo.service;

import java.com.ejemplo.dao.LibroDao;
import java.com.ejemplo.model.Libro;

import java.io.IOException;
import java.util.List;

public class LibroService {
    private LibroDao libroDAO = new LibroDao();

    public void saveLibro(Libro libro) throws IOException {
        libroDAO.save(libro);
    }

    public List<Libro> getAllLibros() throws IOException {
        return libroDAO.findAll();
    }

    public void updateLibro(Libro libro) throws IOException {
        libroDAO.update(libro);
    }

    public void deleteLibro(int id) throws IOException {
        libroDAO.delete(id);
    }

    public Libro getLibroById(int id) throws IOException {
        return libroDAO.findById(id);
    }
}