package com.example.rickandmorty.episode.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.episode.model.Episode
import com.example.rickandmorty.episode.model.EpisodeResult
import com.example.rickandmorty.episode.usecase.EpisodeUseCase
import com.example.rickandmorty.episode.viewmodel.episodeevent.EpisodeEvent
import com.example.rickandmorty.episode.viewmodel.episodeinterector.EpisodeInterector
import com.example.rickandmorty.episode.viewmodel.episodestate.EpisodeState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EpisodeViewModel : ViewModel() {
    private var state: MutableLiveData<EpisodeState> = MutableLiveData()
    val viewState = state
    private var event: MutableLiveData<EpisodeEvent> = MutableLiveData()
    val viewEvent = event
    private val useCase: EpisodeUseCase = EpisodeUseCase()

    fun interpret(interector: EpisodeInterector) {
        when (interector) {
            is EpisodeInterector.ShowList -> getEpisodes(interector.page)
            is EpisodeInterector.EpisodeDetail -> getDetail(interector.episode)
        }
    }

    private fun getEpisodes(page: Int) {
        viewModelScope.launch {
            event.value = EpisodeEvent.Loading(true)
            try {
                val episode: EpisodeResult = withContext(Dispatchers.IO) {
                    useCase.getEpisodeResult(page)
                }
                state.value = EpisodeState.EpisodesListSuccess(episode.episodes)
                event.value = EpisodeEvent.Loading(false)
            } catch (ex: Exception) {
                errorApi(ex.message.toString())
            }
        }
    }

    private fun getDetail(episode: Episode) {
        event.value = EpisodeEvent.ShowEpisodeDetail(episode)
    }

    private fun errorApi(message: String) {
        state.value = EpisodeState.EpisodesListError(message)
    }

}