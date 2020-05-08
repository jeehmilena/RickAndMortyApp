package com.example.rickandmorty.serie.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.serie.model.series.SerieResult
import com.example.rickandmorty.serie.usecase.SeasonUseCase
import com.example.rickandmorty.serie.viewmodel.seasonevent.SeasonEvent
import com.example.rickandmorty.serie.viewmodel.seasoninterector.SeasonInterector
import com.example.rickandmorty.serie.viewmodel.seasonstate.SeasonState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SeasonViewModel : ViewModel() {
    private var state: MutableLiveData<SeasonState> = MutableLiveData()
    val viewState = state
    private var event: MutableLiveData<SeasonEvent> = MutableLiveData()
    val viewEvent = event
    private val useCase: SeasonUseCase = SeasonUseCase()

    fun interpret(interector: SeasonInterector) {
        when (interector) {
            is SeasonInterector.ShowListSeasons -> getSeasons()
        }
    }

    private fun getSeasons() {
        viewModelScope.launch {
            event.value = SeasonEvent.Loading(true)
            try {
                val serie: SerieResult = withContext(Dispatchers.IO) {
                    useCase.getSerieResult()
                }
                state.value = SeasonState.SeasonsListSuccess(serie.seasons)
                event.value = SeasonEvent.Loading(false)
            } catch (ex: Exception) {
                Log.i("excpetion seasons", "-----> ${ex.message}")
                event.value = SeasonEvent.Loading(false)
            }
        }
    }
}