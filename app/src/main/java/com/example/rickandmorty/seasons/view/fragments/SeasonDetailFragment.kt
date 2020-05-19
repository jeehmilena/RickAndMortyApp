package com.example.rickandmorty.seasons.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty.Constants.SEASON_NUMBER
import com.example.rickandmorty.R
import com.example.rickandmorty.seasons.model.season.EpisodeResult
import com.example.rickandmorty.seasons.view.adapter.EpisodesAdapter
import com.example.rickandmorty.seasons.viewmodel.SeasonDetailViewModel
import com.example.rickandmorty.seasons.viewmodel.seasonevent.SeasonEvent
import com.example.rickandmorty.seasons.viewmodel.seasoninterector.SeasonInterector
import com.example.rickandmorty.seasons.viewmodel.seasonstate.SeasonState
import kotlinx.android.synthetic.main.fragment_season_detail.*

/**
 * A simple [Fragment] subclass.
 */
class SeasonDetailFragment : Fragment() {
    private val adapter: EpisodesAdapter by lazy {
        EpisodesAdapter(ArrayList())
    }

    private val viewModel: SeasonDetailViewModel by lazy {
        ViewModelProvider(this).get(
            SeasonDetailViewModel::class.java
        )
    }

    private var seasonNumber: Long = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_season_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.takeIf { it.containsKey(SEASON_NUMBER) }?.apply {
            seasonNumber = getLong(SEASON_NUMBER)
        }

        initViewModel()
        recyclerViewSeasonDetails.layoutManager = LinearLayoutManager(context)
        recyclerViewSeasonDetails.adapter = adapter
    }

    private fun initViewModel() {

        viewModel.viewState.observe(viewLifecycleOwner, Observer { state ->
            state?.let {
                when (it) {
                    is SeasonState.SeasonDetailsListSucces -> showListEpisodes(it.episodes)
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

        viewModel.interpret(SeasonInterector.ShowListSeasonDetail(seasonNumber))
    }

    private fun showListEpisodes(episodes: List<EpisodeResult>) {
        adapter.update(episodes.toMutableList())
    }

    private fun showLoading(status: Boolean) {
        when {
            status -> {
                loading.visibility = View.VISIBLE
            }
            else -> {
                loading.visibility = View.GONE
            }
        }
    }

}
