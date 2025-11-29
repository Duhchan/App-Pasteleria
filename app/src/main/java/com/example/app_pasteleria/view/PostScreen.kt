package com.example.app_pasteleria.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app_pasteleria.viewmodel.PostViewModel

@Composable
fun PostScreen(viewModel: PostViewModel = viewModel()) {
    val posts by viewModel.posts.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Noticias de Pastelería (API)", modifier = Modifier.padding(bottom = 16.dp))

        LazyColumn {
            items(posts) { post ->
                Card(modifier = Modifier.padding(vertical = 8.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = post.title, maxLines = 1) // Título
                        Text(text = post.body, maxLines = 2)  // Contenido
                    }
                }
            }
        }
    }
}