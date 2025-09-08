package com.tugce.programmingjokes.data

import com.tugce.programmingjokes.model.Joke
import retrofit2.http.GET

interface JokesApi {
    // Raw GitHub URL için baseUrl + path kullanacağız
    @GET("atilsamancioglu/ProgrammingJokesJSON/main/jokes.json")
    suspend fun getJokes(): List<Joke>
}