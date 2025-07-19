package com.example.githubclient.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.githubclient.ui.screen.UserListScreen
import com.example.githubclient.ui.screen.UserReposScreen

@Composable
fun NavGraph(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController,
        startDestination = "userList",
        modifier = Modifier.padding(innerPadding)
    ) {
        composable("userList") {
            UserListScreen(navController, viewModel = hiltViewModel())
        }
        composable(
            "userRepos/{username}",
            arguments = listOf(navArgument("username") { type = NavType.StringType })
        ) { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: ""
            UserReposScreen(navController, username, viewModel = hiltViewModel())
        }
    }
}
