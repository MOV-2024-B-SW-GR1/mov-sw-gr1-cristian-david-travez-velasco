# mov-sw-gr1-cristian-david-travez-velasco
# Examen_02

Este proyecto es una implementación en **Kotlin** que simula la gestión de **autores** y **libros**, organizados en una estructura modular siguiendo el patrón **MVC**. Incluye menús interactivos para realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) sobre autores y libros, además de gestionar la relación uno-a-muchos entre ambas entidades.

## Estructura del Proyecto
![image](https://github.com/user-attachments/assets/9d584224-1f4d-4f46-a854-ea650a8024c4)

## Características

- **Gestión de autores:**
  - Crear, ver, actualizar y eliminar autores.
  - Validación para evitar libros sin autor.

- **Gestión de libros:**
  - Crear, ver, actualizar y eliminar libros.
  - Relación uno-a-muchos: cada libro debe asociarse a un autor existente.

- **Persistencia de datos:**
  - Los datos se almacenan en archivos `.txt` ubicados en la carpeta `resources`.

## Requisitos

- **Kotlin**: Versión 1.5 o superior.
- **IntelliJ IDEA**: Se recomienda para ejecutar y modificar el proyecto.
- **JDK**: Versión 8 o superior.

## Configuración del Proyecto

1. Clona el repositorio o descarga los archivos.
2. Asegúrate de que las siguientes carpetas estén configuradas correctamente:
   - `src/` marcada como **Sources Root**.
   - `resources/` marcada como **Resources Root**.
3. En IntelliJ IDEA, configura la clase principal en **Run/Debug Configurations** como:

## MainKto el paquete completo si es necesario (`src.MainKt`).

4. Limpia y reconstruye el proyecto:
- Ve a **Build** → **Clean Project**.
- Luego selecciona **Build** → **Rebuild Project**.

5. Ejecuta el proyecto desde IntelliJ IDEA.

## Ejemplo de Uso

Al ejecutar el programa, verás el siguiente menú interactivo:

### Gestión de Autores

Opciones disponibles:
1. Crear un autor.
2. Ver la lista de autores existentes.
3. Actualizar la información de un autor.
4. Eliminar un autor por su ID.
5. Volver al menú principal.

### Gestión de Libros

Opciones disponibles:
1. Crear un libro asociado a un autor.
2. Ver la lista de libros existentes.
3. Actualizar la información de un libro.
4. Eliminar un libro por su ID.
5. Volver al menú principal.

### Relación de UNO a MUCHOS

Muestra la relación de un autor con sus libros. Si un autor no tiene libros, se indica claramente.

## Archivos de Recursos

- **autores.txt**: Almacena los datos de los autores en formato CSV (`ID,Nombre,FechaNacimiento,Activo`).
- **libros.txt**: Almacena los datos de los libros en formato CSV (`ID,Titulo,Género,Precio,ID_Autor`).

## Contribuciones

Si deseas contribuir al proyecto:
1. Haz un fork del repositorio.
2. Crea una nueva rama: `git checkout -b feature/nueva-funcionalidad`.
3. Realiza los cambios necesarios.
4. Realiza un pull request con tus modificaciones.

---

¡Gracias por usar el proyecto Examen_02! Si tienes alguna pregunta o sugerencia, no dudes en abrir un issue.

