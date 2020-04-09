package com.example.rickandmorty.episode.viewmodel.episodeevent

import com.example.rickandmorty.character.model.Character
import com.example.rickandmorty.episode.model.Episode

sealed class EpisodeEvent {
    data class Loading(val status: Boolean) : EpisodeEvent()
    data class ShowEpisodeDetail(val episode: Episode): EpisodeEvent()
}