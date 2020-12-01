package com.example.desafiowebservices.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiowebservices.R
import com.example.desafiowebservices.entities.Result
import com.example.desafiowebservices.ui.adapters.HomeAdapter
import com.example.desafiowebservices.utilities.MainViewModelFactory

class HomeFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var model: MainViewModel
    lateinit var homeAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

//        val factory = MainViewModelFactory(requireActivity().applicationContext)
//        model = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        model = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        model.listComics.observe(viewLifecycleOwner, {

            homeAdapter = model.listComics.value?.let { context?.let { it1 -> HomeAdapter(it1, it) } }!!

            val recyclerView = view?.findViewById<RecyclerView>(R.id.rvGibis)

            recyclerView?.adapter = homeAdapter
            recyclerView?.layoutManager = GridLayoutManager(context, 3)
            recyclerView?.setHasFixedSize(true)
        })

        return view
    }
}