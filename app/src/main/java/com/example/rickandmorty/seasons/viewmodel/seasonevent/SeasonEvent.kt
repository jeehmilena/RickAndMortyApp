package com.example.rickandmorty.seasons.viewmodel.seasonevent

sealed class SeasonEvent {
    data class Loading(val status: Boolean) : SeasonEvent()
}