package com.example.desafiowebservices.ui

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafiowebservices.R
import com.example.desafiowebservices.entities.Event
import com.example.desafiowebservices.entities.Result
import com.example.desafiowebservices.services.Repository
import com.example.desafiowebservices.services.repository
import kotlinx.coroutines.launch

class MainViewModel(repository: Repository) : ViewModel() {

    private val _navigateScreen = MutableLiveData<Event<Int>>()
    val navigateScreen: MutableLiveData<Event<Int>> = _navigateScreen
    val listComics = MutableLiveData<List<Result>>()

    fun onBtnLoginPressed() {
        _navigateScreen.value = Event(R.id.homeFragment)
    }

    fun onBtnCadastroPressed() {
        _navigateScreen.value = Event(R.id.cadastroFragment)
    }

    fun onBtnSavePressed() {
        _navigateScreen.value = Event(R.id.homeFragment)
    }

    fun onRvGibiPressed() {
        _navigateScreen.value = Event(R.id.detalhesFragment)
    }

    fun popListComics() {
        viewModelScope.launch {
            listComics.value = repository.getComics(
                51,
                10,
                "1",
                "6eb7e8896ec5850c52515a8a23ee97f0",
                "40a3aa568bb269dfad85ae0c4a297181"
            ).data.results
        }
    }

    fun mudarCorDaToolbar(
        context: Context,
        colorId: Int,
        supportActionBar: androidx.appcompat.app.ActionBar?
    ) {
        supportActionBar!!.setBackgroundDrawable(
            ColorDrawable(
                ContextCompat.getColor(
                    context,
                    colorId
                )
            )
        )
    }
}