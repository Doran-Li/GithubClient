package com.example.githubclient.ui.screen

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.githubclient.ui.viewmodel.ReposViewModel
import coil3.compose.AsyncImage
import androidx.core.net.toUri

@Composable
fun UserReposScreen(navController: NavController, username: String, viewModel: ReposViewModel) {
    val context = LocalContext.current
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(username) { viewModel.loadUser(username) }

    Column {
        state.user?.let { user ->
            Row(modifier = Modifier.padding(16.dp)) {
                AsyncImage(
                    model = user.avatarUrl,
                    contentDescription = null,
                    modifier = Modifier.size(80.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(text = "User Name: ${user.login}", style = MaterialTheme.typography.titleLarge)
                    user.name?.let { Text(text = "Full Name: $it", style = MaterialTheme.typography.titleLarge) }
                    Text(text = "Followers: ${user.followers ?: 0}", style = MaterialTheme.typography.titleLarge)
                    Text(text = "Following: ${user.following ?: 0}", style = MaterialTheme.typography.titleLarge)
                }
            }
            HorizontalDivider()
        }
        LazyColumn {
            items(state.repos) { repo ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            context.startActivity(Intent(Intent.ACTION_VIEW, repo.htmlUrl.toUri()))
                        }
                        .padding(8.dp)
                ) {
                    Text(text = "Repository: ${repo.name}", style = MaterialTheme.typography.titleMedium)
                    Text(text = "Language: ${repo.language ?: "Unknown"}", style = MaterialTheme.typography.titleMedium)
                    Text(text = "Stars: ${repo.stars}", style = MaterialTheme.typography.titleMedium)
                    repo.description?.let { Text(text = "Description: $it", style = MaterialTheme.typography.titleMedium) }
                    HorizontalDivider()
                }
            }
        }
    }
}
