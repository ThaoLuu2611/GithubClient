package com.thao.gitclient.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thao.gitclient.model.Repo
import com.thao.gitclient.model.User
import com.thao.gitclient.repository.UserRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class UserViewModel : ViewModel(), CoroutineScope {
    private var job = Job()
    var repository = UserRepository();
    private val _userLiveData = MutableLiveData<User?>();
    val userLiveData: LiveData<User?> = _userLiveData;

    private val _userRepositoryLiveData = MutableLiveData<List<Repo>?>();
    val userRepositoryLiveData: LiveData<List<Repo>?> = _userRepositoryLiveData;
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    val scope = CoroutineScope(coroutineContext)

    fun getGitHubUserProfile(userName: String) {
        scope.launch {
            val response = repository.getUser(userName)
            _userLiveData.postValue(response);
        }
    }

    fun getGitHubUserRepository(userName: String, page: Int) {
        scope.launch {
            val response = repository.getUserRepository(userName, page)
            var list: MutableList<Repo>? = mutableListOf()
            _userRepositoryLiveData.value?.let {
                if (page != 1)
                    list?.addAll(it)

            }
            list?.addAll(response as List<Repo>)
            _userRepositoryLiveData.postValue(list);
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}