package com.example.rickandmorty.serie.view.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.rickandmorty.Constants.SEASON_NUMBER
import com.example.rickandmorty.serie.model.series.Season
import com.example.rickandmorty.serie.view.fragments.SeasonDetailFragment

class SeasonPageAdapter(
    private var listSeasons: MutableList<Season>, fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = listSeasons.size

    override fun createFragment(position: Int): Fragment {
        val seasonDetailFragment = SeasonDetailFragment()
        seasonDetailFragment.arguments = Bundle().apply {
            putLong(SEASON_NUMBER, listSeasons[position].seasonNumber.toLong())
        }
        return seasonDetailFragment
    }
    fun update(list: MutableList<Season>) {

        if (this.listSeasons.isEmpty()) {
            this.listSeasons = list
        } else {
            this.listSeasons.addAll(list)
        }
        notifyDataSetChanged()
    }
}