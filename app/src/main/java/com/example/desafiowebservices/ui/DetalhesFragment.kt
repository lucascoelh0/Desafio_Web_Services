package com.example.desafiowebservices.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.desafiowebservices.R

class DetalhesFragment : Fragment() {

    private lateinit var model: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detalhes, container, false)

        model = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        model.currentPosition.observe(viewLifecycleOwner, {

            val clickedComic = model.currentPosition.value?.let { it1 ->
                model.listComics.value?.get(
                    it1
                )
            }

            if (clickedComic != null) {

                view.findViewById<TextView>(R.id.tvDescricao).text = clickedComic.description

                model.setarImagem(
                    clickedComic.thumbnail.path,
                    clickedComic.thumbnail.extension,
                    view.findViewById(R.id.ivCapa),
                    view.findViewById(R.id.ivCapaGibiDetalhe)
                )

                view.findViewById<TextView>(R.id.tvTitulo).text = clickedComic.title

                model.setarData(clickedComic.dates[0].date, view.findViewById(R.id.tvPublishedDate))

                val preco = "$ " + clickedComic.prices[0].price
                view.findViewById<TextView>(R.id.tvPriceValue).text = preco

                view.findViewById<TextView>(R.id.tvPagesNumber).text =
                    clickedComic.pageCount.toString()
            }
        })

        return view
    }
}