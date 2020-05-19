package com.example.rickandmorty.seasons.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.seasons.model.season.Season
import com.example.rickandmorty.seasons.usecase.SeasonUseCase
import com.example.rickandmorty.seasons.viewmodel.seasonevent.SeasonEvent
import com.example.rickandmorty.seasons.viewmodel.seasoninterector.SeasonInterector
import com.example.rickandmorty.seasons.viewmodel.seasonstate.SeasonState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SeasonDetailViewModel : ViewModel() {
    private var state: MutableLiveData<SeasonState> = MutableLiveData()
    val viewState = state
    private var event: MutableLiveData<SeasonEvent> = MutableLiveData()
    val viewEvent = event
    private val useCase: SeasonUseCase = SeasonUseCase()

    fun interpret(interector: SeasonInterector) {
        when (interector) {
            is SeasonInterector.ShowListSeasonDetail -> getListEpisodes(interector.numberSeason)
        }
    }

    private fun getListEpisodes(numberSeason: Long) {
        viewModelScope.launch {
            event.value = SeasonEvent.Loading(true)
            try {
                val season: Season = withContext(Dispatchers.IO) {
                    useCase.getSeasonDetails(numberSeason)
                }
                state.value = SeasonState.SeasonDetailsListSucces(season.episodes)
                event.value = SeasonEvent.Loading(false)
            } catch (ex: Exception) {
                Log.i("excpetion episodes list", "-----> ${ex.message}")
                event.value = SeasonEvent.Loading(false)
            }
        }
    }
}