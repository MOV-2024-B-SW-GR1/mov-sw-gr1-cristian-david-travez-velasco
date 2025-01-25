package com.ejemplo.controller;

import java.com.ejemplo.model.Autor;
import java.com.ejemplo.service.AutorService;

import java.io.IOException;
import java.util.List;

public class AutorController {
    private AutorService autorService = new AutorService();

    // Crear un nuevo autor
    public void createAutor(Autor autor) throws IOException {
        autorService.saveAutor(autor);
    }

    // Obtener todos los autores
    public List<Autor> getAutores() throws IOException {
        return autorService.getAllAutores();
    }

    // Actualizar un autor existente
    public void updateAutor(Autor autor) throws IOException {
        autorService.updateAutor(autor);
    }

    // Eliminar un autor por ID
    public void deleteAutor(int id) throws IOException {
        autorService.deleteAutor(id);
    }

    // Obtener un autor por ID
    public Autor getAutorById(int id) throws IOException {
        return autorService.getAutorById(id);
    }
}