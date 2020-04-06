package com.example.rickandmorty.character.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.character.model.Character
import com.example.rickandmorty.character.view.adapter.CharacterAdapter
import com.example.rickandmorty.character.viewmodel.CharacterViewModel
import com.example.rickandmorty.character.viewmodel.characterevent.CharacterEvent
import com.example.rickandmorty.character.viewmodel.characterinterector.CharacterInterector
import com.example.rickandmorty.character.viewmodel.characterstate.CharacterState
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_character.*

/**
 * A simple [Fragment] subclass.
 */

const val CHARACTER_DETAIL = "character"

class CharacterFragment : Fragment() {
    private var page = 1
    private val adapter: CharacterAdapter by lazy {
        CharacterAdapter(
            ArrayList(), viewModel
        )
    }

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
        scrollPaginationList()
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

        viewModel.viewEvent.observe(viewLifecycleOwner, Observer { event ->
            event?.let {
                when (it) {
                    is CharacterEvent.Loading -> showLoading(it.status)
                    is CharacterEvent.ShowDetail -> characterDetails(it.character)
                }
            }
        })

        viewModel.interpret(CharacterInterector.ShowList(page))
    }

    private fun showListCharacters(characters: List<Character>) {
        adapter.update(characters.toMutableList())
    }

    private fun showErrorMessage(message: String) {
        Snackbar.make(recyclerViewCharacter, message, Snackbar.LENGTH_LONG).show()
    }

    private fun showLoading(status: Boolean) {
        when {
            status -> {
                loading.visibility = View.VISIBLE
            }
            else -> {
                loading.visibility = View.GONE
            }
        }
    }

    private fun scrollPaginationList() {

        recyclerViewCharacter.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(
                @NonNull recyclerView: RecyclerView, dx: Int, dy: Int
            ) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager?
                val totalItemCount: Int = layoutManager?.itemCount ?: 0
                val lastVisible: Int = layoutManager?.findLastVisibleItemPosition() ?: 0
                val ultimoItem = lastVisible + 5 >= totalItemCount

                if (totalItemCount > 0 && ultimoItem &&
                    viewModel.viewEvent.value == CharacterEvent.Loading(false)
                ) {
                    page++
                    viewModel.interpret(CharacterInterector.ShowList(page))
                }
            }
        })
    }

    private fun characterDetails(character: Character) {
        val bundle = bundleOf(CHARACTER_DETAIL to character)
        NavHostFragment.findNavController(this).navigate(
            R.id.action_navigation_character_to_navigation_character_detail, bundle
        )
    }
}
