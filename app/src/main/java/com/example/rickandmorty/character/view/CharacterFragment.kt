package com.example.rickandmorty.character.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.rickandmorty.R
import com.example.rickandmorty.character.model.Character
import com.example.rickandmorty.character.viewmodel.CharacterViewModel
import com.example.rickandmorty.character.viewmodel.characterstate.CharacterState
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass.
 */
class CharacterFragment : Fragment() {
    private lateinit var recyclerViewCharacters: RecyclerView
    private lateinit var adapter: CharacterAdapter
    private val list = listOf<Character>()
    private lateinit var viewModel: CharacterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_character, container, false)

        initViewModel()

        initViews(view)

        return view
    }

    private fun initViews(view: View) {
        recyclerViewCharacters = view.findViewById(R.id.recyclerViewCharacter)
        recyclerViewCharacters.layoutManager = GridLayoutManager(context, 2)
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(CharacterViewModel::class.java)

        viewModel.initState()

        viewModel.viewState.observe(viewLifecycleOwner, Observer { state ->
            state?.let {
                when (it) {
                    is CharacterState.CharactersListSuccess -> showListCharacters(it.characters)
                    is CharacterState.CharactersListError -> showErrorMessage(it.error)
                }
            }
        })
    }

    private fun showListCharacters(characters: List<Character>) {
        adapter = CharacterAdapter((characters))
        recyclerViewCharacters.adapter = adapter
    }

    private fun showErrorMessage(message: String) {
        Snackbar.make(recyclerViewCharacters, message, Snackbar.LENGTH_LONG).show()
    }
}
