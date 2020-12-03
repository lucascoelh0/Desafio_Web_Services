package com.example.desafiowebservices.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.desafiowebservices.R
import com.example.desafiowebservices.entities.EventObserver
import com.example.desafiowebservices.ui.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.cadastro_body.view.*

class CadastroFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var model: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cadastro, container, false)

        model = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        view.btnSave.setOnClickListener { model.onBtnSavePressed() }

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