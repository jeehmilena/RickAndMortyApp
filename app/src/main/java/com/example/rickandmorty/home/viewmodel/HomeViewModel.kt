package com.example.rickandmorty.home.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.home.model.InformationHome
import com.example.rickandmorty.home.usecase.HomeUseCase
import com.example.rickandmorty.home.viewmodel.homeEvent.HomeEvent
import com.example.rickandmorty.home.viewmodel.homeInterector.HomeInterector
import com.example.rickandmorty.home.viewmodel.homeState.HomeState
import com.example.rickandmorty.seasons.model.series.CreatedBy
import com.example.rickandmorty.seasons.model.series.SerieResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {
    private var state: MutableLiveData<HomeState> = MutableLiveData()
    val viewState = state
    private var event: MutableLiveData<HomeEvent> = MutableLiveData()
    val viewEvent = event
    private val useCase: HomeUseCase = HomeUseCase()

    fun interpret(interector: HomeInterector) {
        when (interector) {
            is HomeInterector.ShowListCreators -> getCreators()
        }
    }

    private fun getCreators() {
        viewModelScope.launch {
            event.value = HomeEvent.Loading(true)
            try {
                val creators: SerieResult = withContext(Dispatchers.IO) {
                    useCase.getCreatorsList()
                }
                state.value = HomeState.CreatorsListSuccess(creators.createdBy)
                event.value = HomeEvent.Loading(false)
            } catch (ex: Exception) {
                Log.i("excpetion creators", "-----> ${ex.message}")
                event.value = HomeEvent.Loading(false)
            }
        }
    }
}