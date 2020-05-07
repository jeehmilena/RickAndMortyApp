package com.example.rickandmorty.serie.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.serie.model.series.Season
import com.example.rickandmorty.serie.view.adapter.SerieAdapter
import com.example.rickandmorty.serie.viewmodel.SerieViewModel
import com.example.rickandmorty.serie.viewmodel.serieevent.SerieEvent
import com.example.rickandmorty.serie.viewmodel.serieinterector.SerieInterector
import com.example.rickandmorty.serie.viewmodel.seriestate.SerieState
import kotlinx.android.synthetic.main.fragment_seasons.*

/**
 * A simple [Fragment] subclass.
 */

const val EPISODE_DETAIL = "episode"

class EpisodeFragment : Fragment() {
    private val adapter: SerieAdapter by lazy {
        SerieAdapter(
            ArrayList(),
            viewModel
        )
    }

    private val viewModel: SerieViewModel by lazy {
        ViewModelProvider(this).get(
            SerieViewModel::class.java
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
        recyclerViewEpisodes.layoutManager = LinearLayoutManager(context)
        recyclerViewEpisodes.adapter = adapter
        //scrollPaginationList()
        initViewModel()
    }

    private fun initViewModel() {

        viewModel.viewState.observe(viewLifecycleOwner, Observer { state ->
            state?.let {
                when (it) {
                    is SerieState.SeasonsListSuccess -> showListSeasons(it.seasons)
                }
            }
        })

        viewModel.viewEvent.observe(viewLifecycleOwner, Observer { event ->
            event?.let {
                when (it) {
                    is SerieEvent.Loading -> showLoading(it.status)
                }
            }
        })

        viewModel.interpret(SerieInterector.ShowList)
    }

    private fun showListSeasons(seasons: List<Season>) {
        adapter.update(seasons.toMutableList())
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

    private fun episodeDetails(season: Season) {
        val bundle = bundleOf(EPISODE_DETAIL to season)
        NavHostFragment.findNavController(this).navigate(
            R.id.action_navigation_episode_to_navigation_episode_detail, bundle
        )
    }
}
