package com.example.githubclient.data.model

import com.google.gson.annotations.SerializedName

data class Repo(
    val name: String,
    @SerializedName("language") val language: String?,
    @SerializedName("stargazers_count") val stars: Int,
    val description: String?,
    @SerializedName("fork") val fork: Boolean,
    @SerializedName("html_url") val htmlUrl: String
)
