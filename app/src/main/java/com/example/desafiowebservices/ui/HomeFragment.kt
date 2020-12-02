package com.example.desafiowebservices.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiowebservices.R
import com.example.desafiowebservices.entities.EventObserver
import com.example.desafiowebservices.ui.adapters.HomeAdapter

class HomeFragment : Fragment(), HomeAdapter.OnClickGibiListener {

    private lateinit var model: MainViewModel
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        model = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        model.listComics.observe(viewLifecycleOwner, {

            homeAdapter = model.listComics.value?.let { HomeAdapter(it, this) }!!

            val recyclerView = view?.findViewById<RecyclerView>(R.id.rvGibis)

            recyclerView?.adapter = homeAdapter
            recyclerView?.layoutManager = GridLayoutManager(context, 3)
            recyclerView?.setHasFixedSize(true)
        })

        return view
    }

    override fun onClickGibi(position: Int) {
        model.currentPosition.value = position
        model.onRvGibiPressed()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        model.navigateScreen.observe(viewLifecycleOwner, EventObserver {
            navController.navigate(it)
        })
    }
}