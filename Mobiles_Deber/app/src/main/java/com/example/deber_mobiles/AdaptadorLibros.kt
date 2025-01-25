package com.example.crud_room_kotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorLibros(
    val listaLibros: MutableList<Libro>,
    val listener: AdaptadorListener
) : RecyclerView.Adapter<AdaptadorLibros.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_libro, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val libro = listaLibros[position]

        holder.tvTitulo.text = libro.titulo
        holder.tvAutor.text = libro.autor

        holder.cvLibro.setOnClickListener {
            listener.onEditItemClick(libro)
        }

        holder.btnBorrar.setOnClickListener {
            listener.onDeleteItemClick(libro)
        }
    }

    override fun getItemCount(): Int {
        return listaLibros.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cvLibro = itemView.findViewById<CardView>(R.id.cvLibro)
        val tvTitulo = itemView.findViewById<TextView>(R.id.tvTitulo)
        val tvAutor = itemView.findViewById<TextView>(R.id.tvAutor)
        val btnBorrar = itemView.findViewById<Button>(R.id.btnBorrar)
    }
}