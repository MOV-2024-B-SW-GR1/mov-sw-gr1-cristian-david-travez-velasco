package java.com.ejemplo.service;

import java.com.ejemplo.dao.AutorDao;
import java.com.ejemplo.model.Autor;

import java.io.IOException;
import java.util.List;

public class AutorService {
    private AutorDao autorDAO = new AutorDao();

    public void saveAutor(Autor autor) throws IOException {
        autorDAO.save(autor);
    }

    public List<Autor> getAllAutores() throws IOException {
        return autorDAO.findAll();
    }

    public void updateAutor(Autor autor) throws IOException {
        autorDAO.update(autor);
    }

    public void deleteAutor(int id) throws IOException {
        autorDAO.delete(id);
    }

    public Autor getAutorById(int id) throws IOException {
        return autorDAO.findById(id);
    }
}