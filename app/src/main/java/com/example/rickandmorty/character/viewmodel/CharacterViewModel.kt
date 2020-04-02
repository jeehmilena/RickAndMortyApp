package com.example.rickandmorty.character.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.character.model.Character
import com.example.rickandmorty.character.model.CharacterResult
import com.example.rickandmorty.character.repository.CharacterRepository
import com.example.rickandmorty.character.viewmodel.characterevent.CharacterEvent
import com.example.rickandmorty.character.viewmodel.characterstate.CharacterState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel() {

    private var state: MutableLiveData<CharacterState> = MutableLiveData()
    val viewState = state
    private var event: MutableLiveData<CharacterEvent> = MutableLiveData()
    val viewEvent = event
    private val repository: CharacterRepository = CharacterRepository()

    fun initState() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val character: CharacterResult = repository.getCharacters()
                state.value = CharacterState.CharactersListSuccess(character.characters)
            } catch (ex: Exception) {
               ex.message
            }
        }
    }

    private fun errorApi(message: String) {
        state.value = CharacterState.CharactersListError(message)
    }

}