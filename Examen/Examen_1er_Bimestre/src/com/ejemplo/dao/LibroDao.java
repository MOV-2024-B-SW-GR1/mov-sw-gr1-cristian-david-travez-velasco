package java.com.ejemplo.dao;

import java.com.ejemplo.model.Libro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LibroDao {
    private static final String FILENAME = "libros.txt";

    // Guardar un libro
    public void save(Libro libro) throws IOException {
        String data = libro.getId() + "," + libro.getTitulo() + "," + libro.getGenero() + "," + libro.getPrecio() + "," + libro.getAutor().getId();
        FileUtil.writeToFile(FILENAME, data);
    }

    // Obtener todos los libros
    public List<Libro> findAll() throws IOException {
        List<Libro> libros = new ArrayList<>();
        List<String> lines = FileUtil.readFromFile(FILENAME);
        for (String line : lines) {
            String[] parts = line.split(",");
            Libro libro = new Libro();
            libro.setId(Integer.parseInt(parts[0]));
            libro.setTitulo(parts[1]);
            libro.setGenero(parts[2]);
            libro.setPrecio(Double.parseDouble(parts[3]));
            // Aquí deberías buscar el autor por ID y asignarlo
            libros.add(libro);
        }
        return libros;
    }

    // Actualizar un libro
    public void update(Libro libro) throws IOException {
        List<Libro> libros = findAll();
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getId() == libro.getId()) {
                libros.set(i, libro);
                break;
            }
        }
        saveAll(libros);
    }

    // Eliminar un libro por ID
    public void delete(int id) throws IOException {
        List<Libro> libros = findAll();
        libros.removeIf(libro -> libro.getId() == id);
        saveAll(libros);
    }

    // Obtener un libro por ID
    public Libro findById(int id) throws IOException {
        List<Libro> libros = findAll();
        for (Libro libro : libros) {
            if (libro.getId() == id) {
                return libro;
            }
        }
        return null;
    }

    // Guardar todos los libros
    private void saveAll(List<Libro> libros) throws IOException {
        StringBuilder data = new StringBuilder();
        for (Libro libro : libros) {
            data.append(libro.getId()).append(",")
                    .append(libro.getTitulo()).append(",")
                    .append(libro.getGenero()).append(",")
                    .append(libro.getPrecio()).append(",")
                    .append(libro.getAutor().getId()).append("\n");
        }
        FileUtil.writeToFile(FILENAME, data.toString(), false);
    }
}