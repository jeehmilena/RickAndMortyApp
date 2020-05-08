package com.example.rickandmorty.serie.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.serie.model.season.EpisodeResult
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_recycler_view_season.view.*

const val URL_BASE_IMAGE = "https://image.tmdb.org/t/p/w500"

class EpisodesAdapter(
    var listEpisodes: MutableList<EpisodeResult>
) :
    RecyclerView.Adapter<EpisodesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler_view_season, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listEpisodes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(listEpisodes[position])
    }

    fun update(list: MutableList<EpisodeResult>) {

        if (this.listEpisodes.isEmpty()) {
            this.listEpisodes = list
        } else {
            this.listEpisodes.addAll(list)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(episode: EpisodeResult) {
            itemView.episode_name.text = episode.name
            itemView.episode_air_date.text = episode.air_date
            itemView.episode_number.text = itemView.resources.getString(R.string.episode_number, episode.episode_number.toString())

            Picasso.get().load(URL_BASE_IMAGE + episode.still_path)
                .placeholder(R.drawable.rick_morty).into(itemView.episode_image)
        }
    }

}