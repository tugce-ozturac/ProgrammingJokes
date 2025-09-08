package com.tugce.programmingjokes.data

import com.tugce.programmingjokes.model.Joke

class JokesRepository(private val api: JokesApi) {
    suspend fun getAllJokes(): List<Joke> = api.getJokes()
}