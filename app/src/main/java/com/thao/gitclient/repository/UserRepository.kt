package com.thao.gitclient.repository

import com.thao.gitclient.model.Repo
import com.thao.gitclient.model.User

class UserRepository {
    suspend fun getUser(user: String): User? {
        val request = RetrofitBuilder.apiClient.getUser(user)
        if(request.isSuccessful) {
            return request.body() as User;
        }
        return null
    }

    suspend fun getUserRepository(user: String, page: Int): List<Repo>? {
        val request = RetrofitBuilder.apiClient.getUserRepository(user, page)
        if(request.isSuccessful) {
            return request.body() as List<Repo>?
        }
        return null
    }

}