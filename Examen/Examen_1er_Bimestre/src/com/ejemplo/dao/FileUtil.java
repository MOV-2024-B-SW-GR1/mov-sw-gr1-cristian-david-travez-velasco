package java.com.ejemplo.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    // Escribir en un archivo
    public static void writeToFile(String filename, String data) throws IOException {
        writeToFile(filename, data, true);
    }

    // Escribir en un archivo con opción de sobrescribir
    public static void writeToFile(String filename, String data, boolean append) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, append))) {
            writer.write(data);
            if (append) {
                writer.newLine();
            }
        }
    }

    // Leer de un archivo
    public static List<String> readFromFile(String filename) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }
}