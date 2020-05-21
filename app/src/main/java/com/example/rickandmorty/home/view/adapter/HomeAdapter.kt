package com.example.rickandmorty.home.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.Constants.DAN_HARMON_IMAGE
import com.example.rickandmorty.Constants.URL_BASE_IMAGE
import com.example.rickandmorty.R
import com.example.rickandmorty.home.model.InformationHome
import com.example.rickandmorty.seasons.model.series.CreatedBy
import com.squareup.picasso.Picasso

class HomeAdapter(var list: MutableList<CreatedBy>) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler_view_home, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    fun update(list: MutableList<CreatedBy>) {
        if (this.list.isEmpty()) {
            this.list = list
        } else {
            this.list.addAll(list)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var name = itemView.findViewById<TextView>(R.id.name_creator)
        private var image = itemView.findViewById<ImageView>(R.id.image_creator)

        fun onBind(creator: CreatedBy){
            name.text = creator.name

            if (creator.profilePath.isNullOrEmpty()){
                Picasso.get().load(DAN_HARMON_IMAGE).placeholder(R.drawable.rick_morty).into(image)
            }else{
                Picasso.get().load(URL_BASE_IMAGE+creator.profilePath).placeholder(R.drawable.rick_morty).into(image)
            }
        }
    }
}