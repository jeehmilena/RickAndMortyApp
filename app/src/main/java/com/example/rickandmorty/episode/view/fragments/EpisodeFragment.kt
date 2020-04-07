package com.example.rickandmorty.episode.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.episode.model.Episode
import com.example.rickandmorty.episode.view.adapter.EpisodeAdapter
import com.example.rickandmorty.episode.viewmodel.EpisodeViewModel
import com.example.rickandmorty.episode.viewmodel.episodeevent.EpisodeEvent
import com.example.rickandmorty.episode.viewmodel.episodeinterector.EpisodeInterector
import com.example.rickandmorty.episode.viewmodel.episodestate.EpisodeState
import kotlinx.android.synthetic.main.fragment_episode.*

/**
 * A simple [Fragment] subclass.
 */
class EpisodeFragment : Fragment() {
    private var page = 1
    private val adapter: EpisodeAdapter by lazy {
        EpisodeAdapter(
            ArrayList()
        )
    }

    private val viewModel: EpisodeViewModel by lazy {
        ViewModelProvider(this).get(
            EpisodeViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_episode, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewEpisodes.layoutManager = LinearLayoutManager(context)
        recyclerViewEpisodes.adapter = adapter
        scrollPaginationList()
        initViewModel()
    }

    private fun initViewModel() {

        viewModel.viewState.observe(viewLifecycleOwner, Observer { state ->
            state?.let {
                when (it) {
                    is EpisodeState.EpisodesListSuccess -> showListEpisodes(it.episodes)
                }
            }
        })

        viewModel.viewEvent.observe(viewLifecycleOwner, Observer { event ->
            event?.let {
                when (it) {
                    is EpisodeEvent.Loading -> showLoading(it.status)
                }
            }
        })

        viewModel.interpret(EpisodeInterector.ShowList(page))
    }

    private fun showListEpisodes(episodes: List<Episode>) {
        adapter.update(episodes.toMutableList())
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

    private fun scrollPaginationList() {

        recyclerViewEpisodes.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(
                @NonNull recyclerView: RecyclerView, dx: Int, dy: Int
            ) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager?
                val totalItemCount: Int = layoutManager?.itemCount ?: 0
                val lastVisible: Int = layoutManager?.findLastVisibleItemPosition() ?: 0
                val ultimoItem = lastVisible + 5 >= totalItemCount

                if (totalItemCount > 0 && ultimoItem &&
                    viewModel.viewEvent.value == EpisodeEvent.Loading(false)
                ) {
                    page++
                    viewModel.interpret(EpisodeInterector.ShowList(page))
                }
            }
        })
    }

}
