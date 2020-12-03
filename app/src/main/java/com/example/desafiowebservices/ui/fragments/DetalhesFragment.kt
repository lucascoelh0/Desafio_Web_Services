package com.example.desafiowebservices.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.desafiowebservices.R
import com.example.desafiowebservices.entities.EventObserver
import com.example.desafiowebservices.ui.viewmodels.MainViewModel

class DetalhesFragment : Fragment() {

    private lateinit var model: MainViewModel
    private lateinit var navController: NavController

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

                model.setarImagem(
                    clickedComic.thumbnail.path,
                    clickedComic.thumbnail.extension,
                    view.findViewById(R.id.ivCapa),
                    view.findViewById(R.id.ivCapaGibiDetalhe)
                )

                model.setarTexto(clickedComic.description, view.findViewById(R.id.tvDescricao))
                model.setarTexto(clickedComic.title, view.findViewById(R.id.tvTitulo))
                model.setarTexto(
                    "$ " + clickedComic.prices[0].price,
                    view.findViewById(R.id.tvPriceValue)
                )
                model.setarTexto(
                    clickedComic.pageCount.toString(),
                    view.findViewById(R.id.tvPagesNumber)
                )
                model.setarData(clickedComic.dates[0].date, view.findViewById(R.id.tvPublishedDate))
            }
        })

        view.findViewById<ImageView>(R.id.ivCapaGibiDetalhe).setOnClickListener {
            model.onIvCapaGibiDetalhePressed()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        model.navigateScreen.observe(viewLifecycleOwner, EventObserver {
            navController.navigate(it)
        })
    }
}