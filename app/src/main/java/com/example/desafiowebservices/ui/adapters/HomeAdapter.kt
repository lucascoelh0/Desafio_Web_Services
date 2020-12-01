package com.example.desafiowebservices.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiowebservices.R
import com.example.desafiowebservices.entities.Result
import com.squareup.picasso.Picasso

class HomeAdapter(val context: Context, val listComics: List<Result>) :
    RecyclerView.Adapter<HomeAdapter.MainViewHolder>() {

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivCapaGibiItem: ImageView = itemView.findViewById(R.id.ivCapaGibiItem)
        var tvNumeroGibi: TextView = itemView.findViewById(R.id.tvNumeroGibi)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_gibi, parent, false)
        return MainViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val gibi = listComics[position]

        val caminhoImagem = gibi.thumbnail.path + "/portrait_xlarge." + gibi.thumbnail.extension

        Picasso.get().load(caminhoImagem).fit().into(holder.ivCapaGibiItem)
        val textoNumero = "#${gibi.issueNumber}"
        holder.tvNumeroGibi.text = textoNumero
    }

    override fun getItemCount() = listComics.size
}