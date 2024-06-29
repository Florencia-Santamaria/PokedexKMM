package com.example.pokedexkmm.android

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedexkmm.DatabaseDriverFactory
import com.example.pokedexkmm.android.databinding.ActivityMainBinding
import com.example.pokedexkmm.data.Pokedex
import com.example.pokedexkmm.data.PokedexResults
import com.example.pokedexkmm.repository.PokedexDBRepository
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var pokedexAdapter: PokedexAdapter
    private lateinit var viewModel: PokedexViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val pokedexRepository = PokedexDBRepository(DatabaseDriverFactory(this))

        pokedexRepository.addPokemon(PokedexResults(name = "Charmender", url = "url123.com"))

        val results= pokedexRepository.getPokemons()

        setContentView(binding.root)

        setupRecyclerView()

        viewModel = ViewModelProvider(this, PokedexViewModelFactory()).get(PokedexViewModel::class.java)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.screenState.collect {
                    when (it) {
                        PokedexScreenState.Loading -> showLoading()
                        PokedexScreenState.Error -> handlerError()
                        is PokedexScreenState.ShowPokedex -> showPokedex(it.pokedex)
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        pokedexAdapter = PokedexAdapter()

        val gridLayoutManager = GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false)
        with(binding.rvPokedex) {
            this.layoutManager = gridLayoutManager
            this.setHasFixedSize(true)
            this.adapter = pokedexAdapter
        }
    }

    private fun showPokedex(pokedex: Pokedex) {
        binding.pokedexProgressBar.visibility = View.GONE
        pokedexAdapter.updatePokedex(pokedex.results)
    }

    private fun handlerError() {
        Toast.makeText(this, "Error buscando la informacion", Toast.LENGTH_LONG).show()
    }

    private fun showLoading() {
        binding.pokedexProgressBar.visibility = View.VISIBLE
    }
}
