package java.com.ejemplo;

import com.ejemplo.controller.AutorController;
import java.com.ejemplo.controller.LibroController;
import java.com.ejemplo.model.Autor;
import java.com.ejemplo.model.Libro;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static AutorController autorController = new AutorController();
    private static LibroController libroController = new LibroController();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("1. Crear Autor");
            System.out.println("2. Listar Autores");
            System.out.println("3. Actualizar Autor");
            System.out.println("4. Eliminar Autor");
            System.out.println("5. Crear Libro");
            System.out.println("6. Listar Libros");
            System.out.println("7. Actualizar Libro");
            System.out.println("8. Eliminar Libro");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (option) {
                case 1:
                    crearAutor(scanner);
                    break;
                case 2:
                    listarAutores();
                    break;
                case 3:
                    actualizarAutor(scanner);
                    break;
                case 4:
                    eliminarAutor(scanner);
                    break;
                case 5:
                    crearLibro(scanner);
                    break;
                case 6:
                    listarLibros();
                    break;
                case 7:
                    actualizarLibro(scanner);
                    break;
                case 8:
                    eliminarLibro(scanner);
                    break;
                case 9:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (option != 9);

        scanner.close();
    }

    private static void crearAutor(Scanner scanner) {
        try {
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Fecha de Nacimiento (yyyy-MM-dd): ");
            Date fechaNacimiento = DATE_FORMAT.parse(scanner.nextLine());
            System.out.print("Activo (true/false): ");
            boolean activo = scanner.nextBoolean();
            scanner.nextLine(); // Consumir el salto de línea

            Autor autor = new Autor(id, nombre, fechaNacimiento, activo, new ArrayList<>());
            autorController.createAutor(autor);
            System.out.println("Autor creado exitosamente.");
        } catch (IOException | ParseException e) {
            System.out.println("Error al crear el autor: " + e.getMessage());
        }
    }

    private static void listarAutores() {
        try {
            List<Autor> autores = autorController.getAutores();
            for (Autor autor : autores) {
                System.out.println(autor);
            }
        } catch (IOException e) {
            System.out.println("Error al listar los autores: " + e.getMessage());
        }
    }

    private static void actualizarAutor(Scanner scanner) {
        try {
            System.out.print("ID del autor a actualizar: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea
            System.out.print("Nuevo Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Nueva Fecha de Nacimiento (yyyy-MM-dd): ");
            Date fechaNacimiento = DATE_FORMAT.parse(scanner.nextLine());
            System.out.print("Activo (true/false): ");
            boolean activo = scanner.nextBoolean();
            scanner.nextLine(); // Consumir el salto de línea

            Autor autor = new Autor(id, nombre, fechaNacimiento, activo, new ArrayList<>());
            autorController.updateAutor(autor);
            System.out.println("Autor actualizado exitosamente.");
        } catch (IOException | ParseException e) {
            System.out.println("Error al actualizar el autor: " + e.getMessage());
        }
    }

    private static void eliminarAutor(Scanner scanner) {
        try {
            System.out.print("ID del autor a eliminar: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            autorController.deleteAutor(id);
            System.out.println("Autor eliminado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al eliminar el autor: " + e.getMessage());
        }
    }

    private static void crearLibro(Scanner scanner) {
        try {
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea
            System.out.print("Título: ");
            String titulo = scanner.nextLine();
            System.out.print("Género: ");
            String genero = scanner.nextLine();
            System.out.print("Precio: ");
            double precio = scanner.nextDouble();
            scanner.nextLine(); // Consumir el salto de línea
            System.out.print("ID del Autor: ");
            int autorId = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            Autor autor = autorController.getAutorById(autorId);
            if (autor == null) {
                System.out.println("Autor no encontrado.");
                return;
            }

            Libro libro = new Libro(id, titulo, genero, precio, autor);
            libroController.createLibro(libro);
            System.out.println("Libro creado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al crear el libro: " + e.getMessage());
        }
    }

    private static void listarLibros() {
        try {
            List<Libro> libros = libroController.getLibros();
            for (Libro libro : libros) {
                System.out.println(libro);
            }
        } catch (IOException e) {
            System.out.println("Error al listar los libros: " + e.getMessage());
        }
    }

    private static void actualizarLibro(Scanner scanner) {
        try {
            System.out.print("ID del libro a actualizar: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea
            System.out.print("Nuevo Título: ");
            String titulo = scanner.nextLine();
            System.out.print("Nuevo Género: ");
            String genero = scanner.nextLine();
            System.out.print("Nuevo Precio: ");
            double precio = scanner.nextDouble();
            scanner.nextLine(); // Consumir el salto de línea
            System.out.print("ID del Autor: ");
            int autorId = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            Autor autor = autorController.getAutorById(autorId);
            if (autor == null) {
                System.out.println("Autor no encontrado.");
                return;
            }

            Libro libro = new Libro(id, titulo, genero, precio, autor);
            libroController.updateLibro(libro);
            System.out.println("Libro actualizado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al actualizar el libro: " + e.getMessage());
        }
    }

    private static void eliminarLibro(Scanner scanner) {
        try {
            System.out.print("ID del libro a eliminar: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            libroController.deleteLibro(id);
            System.out.println("Libro eliminado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al eliminar el libro: " + e.getMessage());
        }
    }
}