package com.example.rickandmorty.character.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.character.model.CharacterResult
import com.example.rickandmorty.character.repository.CharacterRepository
import com.example.rickandmorty.character.viewmodel.characterevent.CharacterEvent

class CharacterViewModel : ViewModel() {

    private var state: MutableLiveData<CharacterResult> = MutableLiveData()
    val viewState = state
    private var event: MutableLiveData<CharacterEvent> = MutableLiveData()
    val viewEvent = event
    private val repository: CharacterRepository = CharacterRepository()



}