package com.example.githubclient.data.repository

import com.example.githubclient.data.remote.GithubApi
import com.example.githubclient.data.model.Repo
import com.example.githubclient.data.model.User
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val api: GithubApi
) : GithubRepository {
    override suspend fun searchUsers(query: String): List<User> = api.searchUsers(query).items
    override suspend fun getUser(username: String): User = api.getUser(username)
    override suspend fun getRepos(username: String): List<Repo> = api.getRepos(username)
}
