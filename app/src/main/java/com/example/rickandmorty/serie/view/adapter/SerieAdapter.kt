package com.example.rickandmorty.serie.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.serie.model.series.Season
import com.example.rickandmorty.serie.viewmodel.SerieViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_recycler_view_season.view.*

const val URL_BASE_IMAGE = "https://image.tmdb.org/t/p/w500"

class SerieAdapter(
    var listSesons: MutableList<Season>,
    val viewModel: SerieViewModel
) :
    RecyclerView.Adapter<SerieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler_view_season, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listSesons.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val season = listSesons[position]
        holder.onBind(season)
    }

    fun update(list: MutableList<Season>) {

        if (this.listSesons.isEmpty()) {
            this.listSesons = list
        } else {
            this.listSesons.addAll(list)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(season: Season) {
            itemView.season_name.text = season.name

            when {
                season.overview.isNotEmpty() -> {
                    itemView.season_overview.visibility = View.VISIBLE
                    itemView.season_overview.text = season.overview
                }
                else -> {
                    itemView.season_overview.visibility = View.VISIBLE
                }
            }

            Picasso.get().load(URL_BASE_IMAGE + season.posterPath)
                .placeholder(R.drawable.rick_morty).into(itemView.season_image)
        }
    }
}