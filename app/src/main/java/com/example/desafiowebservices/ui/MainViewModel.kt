package com.example.desafiowebservices.ui

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafiowebservices.R
import com.example.desafiowebservices.entities.Event
import com.example.desafiowebservices.entities.Result
import com.example.desafiowebservices.services.Repository
import com.example.desafiowebservices.services.repository
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class MainViewModel(repository: Repository) : ViewModel() {

    private val _navigateScreen = MutableLiveData<Event<Int>>()
    val navigateScreen: MutableLiveData<Event<Int>> = _navigateScreen
    val listComics = MutableLiveData<List<Result>>()
    var currentPosition = MutableLiveData<Int>()

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

    fun onIvCapaGibiDetalhePressed() {
        _navigateScreen.value = Event(R.id.capaExpandidaFragment)
    }

    fun popListComics() {
        viewModelScope.launch {
            listComics.value = repository.getComics(
                296,
                10,
                "1",
                "f91ce9ab73c76ef4dcbd24104bd8a3db",
                "a39551871840742cfc7959634ff1d5e7"
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

    fun setarImagem(
        path: String,
        extension: String,
        ivCapa: ImageView,
        ivCapaGibiDetalhe: ImageView
    ) {
        val caminhoImagemThumbnail = "$path/portrait_xlarge.$extension"
        val caminhoImagemCapa = "$path/detail.$extension"

        Picasso.get().load(caminhoImagemThumbnail).fit().into(ivCapaGibiDetalhe)
        Picasso.get().load(caminhoImagemCapa).centerCrop().fit().into(ivCapa)
    }

    fun setarData(published: String, textView: TextView) {

        val data = published.split("T")[0].split("-")
        val dataFormatada: String = data[2] + "/" + data[1] + "/" + data[0]

        textView.text = dataFormatada
    }

    fun setarTexto(text: String, textView: TextView) {
        textView.text = text
    }

    fun setarImagemExpandida(path: String, extension: String, ivCapaExpandida: ImageView) {
        val caminhoImagemCapa = "$path/detail.$extension"
        Picasso.get().load(caminhoImagemCapa).fit().into(ivCapaExpandida)
    }
}