package com.example.rickandmorty.character.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.character.model.CharacterResult
import com.example.rickandmorty.character.usecase.CharacterUseCase
import com.example.rickandmorty.character.viewmodel.characterevent.CharacterEvent
import com.example.rickandmorty.character.viewmodel.characterinterector.CharacterInterector
import com.example.rickandmorty.character.viewmodel.characterstate.CharacterState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterViewModel : ViewModel() {

    private var state: MutableLiveData<CharacterState> = MutableLiveData()
    val viewState = state
    private var event: MutableLiveData<CharacterEvent> = MutableLiveData()
    val viewEvent = event
    private val useCase: CharacterUseCase = CharacterUseCase()

    fun interpret(interector: CharacterInterector) {
        when (interector) {
            is CharacterInterector.ShowList -> getCharacters()
        }
    }

    private fun getCharacters() {
        viewModelScope.launch {
            try {
                val character: CharacterResult = withContext(Dispatchers.IO) {
                    useCase.getCharacterResult()
                }
                state.value = CharacterState.CharactersListSuccess(character.characters)
            } catch (ex: Exception) {
                errorApi(ex.message.toString())
            }
        }
    }

    private fun errorApi(message: String) {
        state.value = CharacterState.CharactersListError(message)
    }
}