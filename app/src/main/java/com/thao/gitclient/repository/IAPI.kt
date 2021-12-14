package com.thao.gitclient.repository

import com.thao.gitclient.model.Repo
import com.thao.gitclient.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IAPI{
    @GET("users/{user}")
     suspend fun getUser(@Path("user") user: String?): Response<User>

    @GET("users/{user}/repos")
    suspend fun getUserRepository(@Path("user") user: String?, @Query("page") page: Int): Response<List<Repo>?>
}