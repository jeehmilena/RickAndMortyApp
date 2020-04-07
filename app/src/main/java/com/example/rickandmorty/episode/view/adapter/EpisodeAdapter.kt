package com.example.rickandmorty.episode.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.episode.model.Episode
import kotlinx.android.synthetic.main.item_recycler_view_episode.view.*

class EpisodeAdapter(var listEpisodes: MutableList<Episode>) :
    RecyclerView.Adapter<EpisodeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler_view_episode, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listEpisodes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(listEpisodes[position])
    }


    fun update(list: MutableList<Episode>) {

        if (this.listEpisodes.isEmpty()) {
            this.listEpisodes = list
        } else {
            this.listEpisodes.addAll(list)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(episode: Episode) {

            when (episode.episode) {
                "S01E01", "S02E01", "S03E01" -> {
                    itemView.episode_season.text = episode.episode
                    itemView.linearLayoutSeason.visibility = View.VISIBLE
                }
                else ->
                    itemView.linearLayoutSeason.visibility = View.GONE
            }

            itemView.episode_name.text = episode.name

        }
    }
}