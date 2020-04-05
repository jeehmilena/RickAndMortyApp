package com.example.rickandmorty.character.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val character = arguments?.getParcelable<Character>(CHARACTER_DETAIL)

        Picasso.get().load(character?.image).into(character_image_detail)

    }

}
