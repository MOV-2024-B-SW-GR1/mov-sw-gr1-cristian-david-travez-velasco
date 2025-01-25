package com.example.crud_room_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.crud_room_kotlin.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), AdaptadorListener {

    private lateinit var binding: ActivityMainBinding
    private val listaLibros: MutableList<Libro> = mutableListOf()
    private lateinit var adaptador: AdaptadorLibros
    private lateinit var room: DBPrueba
    private lateinit var libro: Libro

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvLibros.layoutManager = LinearLayoutManager(this)

        room = Room.databaseBuilder(this, DBPrueba::class.java, "dbLibros").build()

        obtenerLibros(room)

        binding.btnAddUpdate.setOnClickListener {
            if (binding.etTitulo.text.isNullOrEmpty() || binding.etAutor.text.isNullOrEmpty()) {
                Toast.makeText(this, "DEBES LLENAR TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (binding.btnAddUpdate.text.equals("agregar")) {
                libro = Libro(
                    binding.etTitulo.text.toString().trim(),
                    binding.etAutor.text.toString().trim()
                )
                agregarLibro(room, libro)
            } else if (binding.btnAddUpdate.text.equals("actualizar")) {
                libro.autor = binding.etAutor.text.toString().trim()
                actualizarLibro(room, libro)
            }
        }
    }

    private fun obtenerLibros(room: DBPrueba) {
        lifecycleScope.launch {
            listaLibros.clear()
            listaLibros.addAll(room.daoLibro().obtenerLibros())
            adaptador = AdaptadorLibros(listaLibros, this@MainActivity)
            binding.rvLibros.adapter = adaptador
        }
    }

    private fun agregarLibro(room: DBPrueba, libro: Libro) {
        lifecycleScope.launch {
            room.daoLibro().agregarLibro(libro)
            obtenerLibros(room)
            limpiarCampos()
        }
    }

    private fun actualizarLibro(room: DBPrueba, libro: Libro) {
        lifecycleScope.launch {
            room.daoLibro().actualizarLibro(libro.titulo, libro.autor)
            obtenerLibros(room)
            limpiarCampos()
        }
    }

    private fun limpiarCampos() {
        libro.titulo = ""
        libro.autor = ""
        binding.etTitulo.setText("")
        binding.etAutor.setText("")

        if (binding.btnAddUpdate.text.equals("actualizar")) {
            binding.btnAddUpdate.text = "agregar"
            binding.etTitulo.isEnabled = true
        }
    }

    override fun onEditItemClick(libro: Libro) {
        binding.btnAddUpdate.text = "actualizar"
        binding.etTitulo.isEnabled = false
        this.libro = libro
        binding.etTitulo.setText(this.libro.titulo)
        binding.etAutor.setText(this.libro.autor)
    }

    override fun onDeleteItemClick(libro: Libro) {
        lifecycleScope.launch {
            room.daoLibro().borrarLibro(libro.titulo)
            adaptador.notifyDataSetChanged()
            obtenerLibros(room)
        }
    }
}