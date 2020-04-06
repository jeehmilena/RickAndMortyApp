package com.example.rickandmorty.character.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import kotlinx.android.synthetic.main.item_recycler_view_episodes_character_detail.view.*

class EpisodesCharacterDetailAdapter(var listEpisodes: MutableList<String>) :
    RecyclerView.Adapter<EpisodesCharacterDetailAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler_view_episodes_character_detail, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listEpisodes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(listEpisodes[position])
    }

    fun update(list: MutableList<String>) {

        if (this.listEpisodes.isEmpty()) {
            this.listEpisodes = list
        } else {
            this.listEpisodes.addAll(list)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(episode: String) {
            itemView.name_episode.text = episode
        }
    }
}