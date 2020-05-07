package com.example.rickandmorty.serie.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.serie.model.series.SerieResult
import com.example.rickandmorty.serie.usecase.SerieUseCase
import com.example.rickandmorty.serie.viewmodel.serieevent.SerieEvent
import com.example.rickandmorty.serie.viewmodel.serieinterector.SerieInterector
import com.example.rickandmorty.serie.viewmodel.seriestate.SerieState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SerieViewModel : ViewModel() {
    private var state: MutableLiveData<SerieState> = MutableLiveData()
    val viewState = state
    private var event: MutableLiveData<SerieEvent> = MutableLiveData()
    val viewEvent = event
    private val useCase: SerieUseCase = SerieUseCase()

    fun interpret(interector: SerieInterector) {
        when (interector) {
            is SerieInterector.ShowList -> getSeasons()
        }
    }

    private fun getSeasons() {
        viewModelScope.launch {
            event.value = SerieEvent.Loading(true)
            try {
                val serie: SerieResult = withContext(Dispatchers.IO) {
                    useCase.getSerieResult()
                }
                state.value = SerieState.SeasonsListSuccess(serie.seasons)
                event.value = SerieEvent.Loading(false)
            } catch (ex: Exception) {
                Log.i("excpetion seasons", "-----> ${ex.message}")
                event.value = SerieEvent.Loading(false)
            }
        }
    }
}