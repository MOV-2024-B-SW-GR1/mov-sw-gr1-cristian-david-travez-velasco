package java.com.ejemplo.dao;

import java.com.ejemplo.model.Autor;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AutorDao {
    private static final String FILENAME = "autores.txt";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    // Guardar un autor
    public void save(Autor autor) throws IOException {
        String data = autor.getId() + "," + autor.getNombre() + "," + DATE_FORMAT.format(autor.getFechaNacimiento()) + "," + autor.isActivo();
        FileUtil.writeToFile(FILENAME, data);
    }

    // Obtener todos los autores
    public List<Autor> findAll() throws IOException {
        List<Autor> autores = new ArrayList<>();
        List<String> lines = FileUtil.readFromFile(FILENAME);
        for (String line : lines) {
            String[] parts = line.split(",");
            Autor autor = new Autor();
            autor.setId(Integer.parseInt(parts[0]));
            autor.setNombre(parts[1]);
            try {
                autor.setFechaNacimiento(DATE_FORMAT.parse(parts[2]));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            autor.setActivo(Boolean.parseBoolean(parts[3]));
            autores.add(autor);
        }
        return autores;
    }

    // Actualizar un autor
    public void update(Autor autor) throws IOException {
        List<Autor> autores = findAll();
        for (int i = 0; i < autores.size(); i++) {
            if (autores.get(i).getId() == autor.getId()) {
                autores.set(i, autor);
                break;
            }
        }
        saveAll(autores);
    }

    // Eliminar un autor por ID
    public void delete(int id) throws IOException {
        List<Autor> autores = findAll();
        autores.removeIf(autor -> autor.getId() == id);
        saveAll(autores);
    }

    // Obtener un autor por ID
    public Autor findById(int id) throws IOException {
        List<Autor> autores = findAll();
        for (Autor autor : autores) {
            if (autor.getId() == id) {
                return autor;
            }
        }
        return null;
    }

    // Guardar todos los autores
    private void saveAll(List<Autor> autores) throws IOException {
        StringBuilder data = new StringBuilder();
        for (Autor autor : autores) {
            data.append(autor.getId()).append(",")
                    .append(autor.getNombre()).append(",")
                    .append(DATE_FORMAT.format(autor.getFechaNacimiento())).append(",")
                    .append(autor.isActivo()).append("\n");
        }
        FileUtil.writeToFile(FILENAME, data.toString(), false);
    }
}