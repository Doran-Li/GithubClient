package com.example.githubclient.data.remote

import com.example.githubclient.data.model.Repo
import com.example.githubclient.data.model.User
import com.example.githubclient.data.remote.response.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {
    @GET("search/users")
    suspend fun searchUsers(@Query("q") query: String): SearchResponse

    @GET("users/{username}")
    suspend fun getUser(@Path("username") username: String): User

    @GET("users/{username}/repos")
    suspend fun getRepos(@Path("username") username: String): List<Repo>
}
