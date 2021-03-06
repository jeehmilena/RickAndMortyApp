package com.example.rickandmorty.character.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.rickandmorty.Constants.CHARACTER_DETAIL_KEY
import com.example.rickandmorty.R
import com.example.rickandmorty.character.model.Character
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_character_detail.*

/**
 * A simple [Fragment] subclass.
 */
class CharacterDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_character_detail, container, false)
        setHasOptionsMenu(true)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showInfoDetailsCharacter()
    }

    private fun showInfoDetailsCharacter() {
        val character = arguments?.getParcelable<Character>(CHARACTER_DETAIL_KEY)
        Picasso.get().load(character?.image).into(character_image_detail)
        character_name_datail_info.text = getString(
            R.string.character_name_detail,
            character?.name
        )
        character_status_datail_info.text = getString(
            R.string.character_status_detail,
            character?.status
        )
        character_species_datail_info.text = getString(
            R.string.character_species_detail,
            character?.species
        )
        character_origin_datail_info.text = getString(
            R.string.character_origin_detail,
            character?.origin?.name
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                NavHostFragment.findNavController(this).popBackStack()
            }
        }
        return true
    }
}
