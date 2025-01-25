package java.com.ejemplo.model;

import java.util.Date;
import java.util.List;

public class Autor {
    private int id;
    private String nombre;
    private Date fechaNacimiento;
    private boolean activo;
    private List<Libro> libros;

    // Constructor por defecto
    public Autor() {
    }
    // Constructor
    public Autor(int id, String nombre, Date fechaNacimiento, boolean activo, List<Libro> libros) {
        this.id = id;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.activo = activo;
        this.libros = libros;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    // Método toString
    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", activo=" + activo +
                ", libros=" + libros +
                '}';
    }
}
