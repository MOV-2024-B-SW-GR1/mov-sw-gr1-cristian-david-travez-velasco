package model

import java.util.Date



data class Autor(
    var id: Int = 0,
    var nombre: String = "",
    var fechaNacimiento: Date = Date(),
    var activo: Boolean = true
)
