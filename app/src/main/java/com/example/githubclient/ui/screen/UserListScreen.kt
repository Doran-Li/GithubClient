package com.example.githubclient.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.githubclient.ui.viewmodel.UsersViewModel
import coil3.compose.AsyncImage


@Composable
fun UserListScreen(navController: NavController, viewModel: UsersViewModel) {
    val state by viewModel.uiState.collectAsState()

    Column {
        OutlinedTextField(
            value = state.query,
            onValueChange = { viewModel.onQueryChanged(it) },
            placeholder = { Text("Search users") },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
        HorizontalDivider()
        LazyColumn {
            items(state.users) { user ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navController.navigate("userRepos/${user.login}") }
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = user.avatarUrl,
                        contentDescription = null,
                        modifier = Modifier.size(56.dp),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = user.login, style = MaterialTheme.typography.titleLarge)
                }
                HorizontalDivider()
            }
        }
    }
}
