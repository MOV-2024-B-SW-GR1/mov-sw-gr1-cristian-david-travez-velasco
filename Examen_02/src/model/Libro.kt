package model

import model.Autor


data class Libro(
    var id: Int = 0,
    var titulo: String = "",
    var genero: String = "",
    var precio: Double = 0.0,
    var autor: Autor = Autor()
)
