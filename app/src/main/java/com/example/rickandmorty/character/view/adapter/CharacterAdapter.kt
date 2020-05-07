package com.example.rickandmorty.character.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.character.model.Character
import com.example.rickandmorty.character.viewmodel.CharacterViewModel
import com.example.rickandmorty.character.viewmodel.characterinterector.CharacterInterector
import com.squareup.picasso.Picasso

class CharacterAdapter(
    var list: MutableList<Character>,
    private val viewModel: CharacterViewModel
) :
    RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler_view_character, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = list[position]
        holder.onBind(character)
        holder.itemView.setOnClickListener {
            viewModel.interpret(CharacterInterector.CharacterDetail(character))
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var characterName = itemView.findViewById<TextView>(R.id.character_name)
        private var characterImage = itemView.findViewById<ImageView>(R.id.character_image)

        fun onBind(character: Character) {
            characterName.text = character.name
            Picasso.get().load(character.image).into(characterImage)
        }
    }

    fun update(list: MutableList<Character>) {

        if (this.list.isEmpty()) {
            this.list = list
        } else {
            this.list.addAll(list)
        }
        notifyDataSetChanged()
    }
}