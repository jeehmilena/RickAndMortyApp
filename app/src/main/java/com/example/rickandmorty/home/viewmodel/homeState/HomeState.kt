package com.example.rickandmorty.home.viewmodel.homeState

import com.example.rickandmorty.seasons.model.series.CreatedBy

sealed class HomeState {
    data class CreatorsListSuccess(val creators: List<CreatedBy>) : HomeState()
}