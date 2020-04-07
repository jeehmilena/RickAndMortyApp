package com.example.rickandmorty.episode.viewmodel.episodestate

import com.example.rickandmorty.episode.model.Episode

sealed class EpisodeState {
    data class EpisodesListSuccess(val episodes: List<Episode>) : EpisodeState()
    data class EpisodesListError(val error: String) : EpisodeState()
}