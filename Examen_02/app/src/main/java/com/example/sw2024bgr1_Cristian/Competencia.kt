package com.example.sw2024bgr1_maal

data class Competencia(
    val id: Int = 0,
    val nombre: String,
    val fecha: String,
    val capacidad: Int,
    val cuota: Double,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)
