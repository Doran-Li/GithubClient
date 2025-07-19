package com.example.githubclient.data.model

import com.google.gson.annotations.SerializedName

data class User(
    val login: String,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("name") val name: String?,
    @SerializedName("followers") val followers: Int?,
    @SerializedName("following") val following: Int?
)
