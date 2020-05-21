package com.example.rickandmorty.home.viewmodel.homeEvent

sealed class HomeEvent {
    data class Loading(val status: Boolean) : HomeEvent()
}