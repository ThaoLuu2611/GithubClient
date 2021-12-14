package com.thao.gitclient.model

data class Repo(
    val name: String?,
    val private: Boolean?,
    val description: String?,
    val updated_at: String?,
    val language: String?,
    val stargazers_count: Int?
)