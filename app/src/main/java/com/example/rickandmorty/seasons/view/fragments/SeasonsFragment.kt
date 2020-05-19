package com.example.rickandmorty.seasons.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.R
import com.example.rickandmorty.seasons.model.series.Season
import com.example.rickandmorty.seasons.view.adapter.SeasonPageAdapter
import com.example.rickandmorty.seasons.viewmodel.SeasonViewModel
import com.example.rickandmorty.seasons.viewmodel.seasonevent.SeasonEvent
import com.example.rickandmorty.seasons.viewmodel.seasoninterector.SeasonInterector
import com.example.rickandmorty.seasons.viewmodel.seasonstate.SeasonState
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_seasons.*

/**
 * A simple [Fragment] subclass.
 */

class SeasonFragment : Fragment() {
    private val pageAdapter: SeasonPageAdapter by lazy {
        SeasonPageAdapter(ArrayList(), this)
    }

    private val viewModel: SeasonViewModel by lazy {
        ViewModelProvider(this).get(
            SeasonViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_seasons, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {

        viewModel.viewState.observe(viewLifecycleOwner, Observer { state ->
            state?.let {
                when (it) {
                    is SeasonState.SeasonsListSuccess -> showListSeasons(it.seasons)
                }
            }
        })

        viewModel.viewEvent.observe(viewLifecycleOwner, Observer { event ->
            event?.let {
                when (it) {
                    is SeasonEvent.Loading -> showLoading(it.status)
                }
            }
        })

        viewModel.interpret(SeasonInterector.ShowListSeasons)
    }

    private fun showListSeasons(seasons: List<Season>) {
        pageAdapter.update(seasons.toMutableList())

        viewPagerSeasonDetails.adapter = pageAdapter

        TabLayoutMediator(tabSeasons, viewPagerSeasonDetails,
            fun(tab: TabLayout.Tab, position: Int) {
                tab.text = seasons[position].name
            }).attach()
    }

    private fun showLoading(status: Boolean) {
        when {
            status -> {
                loadingEpisodes.visibility = View.VISIBLE
            }
            else -> {
                loadingEpisodes.visibility = View.GONE
            }
        }
    }
}
