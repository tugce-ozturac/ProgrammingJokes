package com.tugce.programmingjokes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tugce.programmingjokes.data.JokesRepository

class JokesViewModelFactory(private val repo: JokesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JokesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return JokesViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}