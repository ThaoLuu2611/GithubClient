package com.thao.gitclient.repository

import com.thao.gitclient.model.Repo
import com.thao.gitclient.model.User
import retrofit2.Response

class APIClient(private  val apiService: IAPI) {
    suspend fun getUser(userName: String): Response<User> {
        return apiService.getUser(userName);
    }

    suspend fun getUserRepository(userName: String, page: Int): Response<List<Repo>?> {
        return apiService.getUserRepository(userName, page);
    }
}