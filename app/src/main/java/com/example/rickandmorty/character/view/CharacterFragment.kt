package com.example.rickandmorty.character.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmorty.R
import com.example.rickandmorty.character.model.Character
import com.example.rickandmorty.character.viewmodel.CharacterViewModel
import com.example.rickandmorty.character.viewmodel.characterinterector.CharacterInterector
import com.example.rickandmorty.character.viewmodel.characterstate.CharacterState
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_character.*

/**
 * A simple [Fragment] subclass.
 */
class CharacterFragment : Fragment() {
    private val adapter: CharacterAdapter by lazy { CharacterAdapter(ArrayList()) }

    private val viewModel: CharacterViewModel by lazy {
        ViewModelProvider(this).get(
            CharacterViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewCharacter.layoutManager = GridLayoutManager(context, 2)
        recyclerViewCharacter.adapter = adapter
        initViewModel()
    }

    private fun initViewModel() {

        viewModel.viewState.observe(viewLifecycleOwner, Observer { state ->
            state?.let {
                when (it) {
                    is CharacterState.CharactersListSuccess -> showListCharacters(it.characters)
                    is CharacterState.CharactersListError -> showErrorMessage(it.error)
                }
            }
        })

        viewModel.interpret(CharacterInterector.ShowList)
    }

    private fun showListCharacters(characters: List<Character>) {
        adapter.update(characters)
    }

    private fun showErrorMessage(message: String) {
        Snackbar.make(recyclerViewCharacter, message, Snackbar.LENGTH_LONG).show()
    }
}
