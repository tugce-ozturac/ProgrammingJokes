package com.tugce.programmingjokes.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tugce.programmingjokes.model.Joke
import com.tugce.programmingjokes.viewmodel.JokesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JokesScreen(viewModel: JokesViewModel) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(topBar = {
        TopAppBar(title = { Text("Programming Jokes") })
    }) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {
            when {
                state.loading -> {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
                state.error != null -> {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.align(Alignment.Center)
                    ) {
                        Text("Hata: ${state.error}")
                        Spacer(Modifier.height(8.dp))
                        Button(onClick = { viewModel.loadJokes() }) { Text("Tekrar dene") }
                    }
                }
                else -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(state.jokes) { joke ->
                            JokeItem(joke)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun JokeItem(joke: Joke) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                if (joke.type == "twopart") {
                    expanded = !expanded
                }
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            if (joke.type == "single") {
                Text(
                    text = joke.joke ?: "",
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            if (joke.type == "twopart") {
                Text(
                    text = joke.setup ?: "(setup yok)",
                    style = MaterialTheme.typography.bodyLarge
                )

                if (expanded) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = joke.delivery ?: "(delivery yok)",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
    }
}
