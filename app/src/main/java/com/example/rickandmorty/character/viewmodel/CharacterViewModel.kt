package com.example.rickandmorty.character.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.character.model.CharacterResult
import com.example.rickandmorty.character.model.Result
import com.example.rickandmorty.character.repository.CharacterRepository
import com.example.rickandmorty.character.viewmodel.characterevent.CharacterEvent
import com.example.rickandmorty.character.viewmodel.characterstate.CharacterState
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel() {

    private var state: MutableLiveData<CharacterResult> = MutableLiveData()
    val viewState = state
    private var event: MutableLiveData<CharacterEvent> = MutableLiveData()
    val viewEvent = event
    private val repository: CharacterRepository = CharacterRepository()



}