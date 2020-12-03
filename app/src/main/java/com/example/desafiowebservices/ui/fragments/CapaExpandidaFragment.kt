package com.example.desafiowebservices.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.desafiowebservices.R
import com.example.desafiowebservices.ui.viewmodels.MainViewModel

class CapaExpandidaFragment : Fragment() {

    private lateinit var model: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_capa_expandida, container, false)

        model = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        val clickedComic = model.currentPosition.value?.let { model.listComics.value?.get(it) }

        if (clickedComic != null) {
            model.setarImagemExpandida(
                clickedComic.thumbnail.path,
                clickedComic.thumbnail.extension,
                view.findViewById(R.id.ivCapaExpandida)
            )
        }

        return view
    }
}