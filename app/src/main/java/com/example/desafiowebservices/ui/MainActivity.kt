package com.example.desafiowebservices.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.desafiowebservices.R
import com.example.desafiowebservices.services.repository
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    private val viewModel by viewModels<MainViewModel>() {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(repository) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.popListComics()

        val toolbar = findViewById<Toolbar>(R.id.tbMain)
        setSupportActionBar(toolbar)

        navController = findNavController(R.id.navHostMain)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.cadastroFragment -> {
                    supportActionBar?.setDisplayShowCustomEnabled(false)
                    viewModel.mudarCorDaToolbar(this, R.color.red, supportActionBar)
                }
                R.id.homeFragment -> {
                    supportActionBar?.setDisplayShowCustomEnabled(true)

                    val view = LayoutInflater.from(this).inflate(R.layout.custom_toolbar_imageview, null)
                    supportActionBar?.customView = view.findViewById(R.id.customToolbar)

                    viewModel.mudarCorDaToolbar(this, R.color.red, supportActionBar)
                }
                R.id.detalhesFragment, R.id.loginFragment -> {
                    supportActionBar?.setDisplayShowCustomEnabled(false)
                    viewModel.mudarCorDaToolbar(this, R.color.transparent, supportActionBar)
                }
            }
        }

        appBarConfiguration = AppBarConfiguration
            .Builder(
                R.id.loginFragment,
                R.id.homeFragment
            )
            .build()

        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}