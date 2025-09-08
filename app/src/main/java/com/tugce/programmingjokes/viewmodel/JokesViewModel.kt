package com.tugce.programmingjokes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tugce.programmingjokes.model.Joke
import com.tugce.programmingjokes.data.JokesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class JokesUiState(
    val loading: Boolean = false,
    val jokes: List<Joke> = emptyList(),
    val error: String? = null
)

class JokesViewModel(private val repository: JokesRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(JokesUiState(loading = true))
    val uiState: StateFlow<JokesUiState> = _uiState

    init { loadJokes() }

    fun loadJokes() {
        viewModelScope.launch {
            _uiState.value = JokesUiState(loading = true)
            try {
                val jokes = repository.getAllJokes()
                _uiState.value = JokesUiState(loading = false, jokes = jokes)
            } catch (e: Exception) {
                _uiState.value = JokesUiState(loading = false, error = e.localizedMessage ?: "Bilinmeyen hata")
            }
        }
    }
}