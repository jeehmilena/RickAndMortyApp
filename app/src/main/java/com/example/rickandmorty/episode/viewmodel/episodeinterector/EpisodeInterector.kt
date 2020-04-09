package com.example.rickandmorty.episode.viewmodel.episodeinterector

import com.example.rickandmorty.episode.model.Episode

sealed class EpisodeInterector {
    data class ShowList(val page: Int) : EpisodeInterector()
    data class EpisodeDetail(val episode: Episode) : EpisodeInterector()
}