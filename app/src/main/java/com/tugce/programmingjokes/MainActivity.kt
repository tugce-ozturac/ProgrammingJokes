package com.tugce.programmingjokes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.lifecycle.ViewModelProvider
import com.tugce.programmingjokes.screens.JokesScreen
import com.tugce.programmingjokes.data.JokesRepository
import com.tugce.programmingjokes.data.RetrofitInstance
import com.tugce.programmingjokes.viewmodel.JokesViewModel
import com.tugce.programmingjokes.viewmodel.JokesViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val api = RetrofitInstance.api
        val repo = JokesRepository(api)
        val factory = JokesViewModelFactory(repo)
        val vm = ViewModelProvider(this, factory).get(JokesViewModel::class.java)

        setContent {
            MaterialTheme {
                JokesScreen(viewModel = vm)
            }
        }
    }
}