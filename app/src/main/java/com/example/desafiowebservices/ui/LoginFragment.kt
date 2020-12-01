package com.example.desafiowebservices.ui

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
import com.example.desafiowebservices.utilities.MainViewModelFactory
import kotlinx.android.synthetic.main.login_body.*
import kotlinx.android.synthetic.main.login_body.view.*


class LoginFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var model: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val factory = MainViewModelFactory(requireActivity().applicationContext)
        model = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        view.btnLogin.setOnClickListener { model.onBtnLoginPressed() }
        view.btnCreateAccount.setOnClickListener { model.onBtnCadastroPressed() }

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