package com.example.githubclient.data.repository

import com.example.githubclient.data.model.Repo
import com.example.githubclient.data.model.User

interface GithubRepository {
    suspend fun searchUsers(query: String): List<User>
    suspend fun getUser(username: String): User
    suspend fun getRepos(username: String): List<Repo>
}
