package com.thao.gitclient.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.thao.gitclient.databinding.UserProfileFragmentBinding
import com.thao.gitclient.ui.viewmodel.UserViewModel
import androidx.recyclerview.widget.RecyclerView

class UserProfileFragment : Fragment() {
    private lateinit var binding: UserProfileFragmentBinding
    private var loginUser: String? = null
    private var noOfPaging: Int = 0
    private var pagingCounter = 1
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = UserProfileFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val viewModel: UserViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        updateUserProfileUI()
        updateUserRepoProfileUI()
    }

    private fun updateUserProfileUI() {
        viewModel.userLiveData.observe(this) { user ->
            if (user == null) {
                binding.user = null

            } else {
                binding.user = user
                loginUser = user.login
                user.public_repos?.let {
                    noOfPaging = it / 30 + 1
                }
                getUserRepo()
                Glide.with(this).load(user.avatar_url).circleCrop().into(binding.userAvatar)
            }
        }
    }

    fun getUserRepo() {
        Log.d("thaomoc", " pagingCounter $pagingCounter noOfPaging $noOfPaging")
        loginUser?.let {
            if (pagingCounter <= noOfPaging) {
                viewModel.getGitHubUserRepository(it, pagingCounter)
                pagingCounter++
            }
        }
    }

    private fun updateUserRepoProfileUI() {
        viewModel.userRepositoryLiveData.observe(this) { response ->
            if (response == null) {
                binding.user = null

            } else {
                val adapter = RepoAdapter(response, this.context)
                binding.rcvRepo.layoutManager = LinearLayoutManager(context)
                binding.rcvRepo.adapter = adapter
                binding.rcvRepo.addItemDecoration(
                    DividerItemDecoration(
                        this.context,
                        LinearLayoutManager.VERTICAL
                    )
                )
                binding.rcvRepo.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                        super.onScrollStateChanged(recyclerView, newState)
                        if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                            Log.d("thaomoc", "scroll to end end")
                            getUserRepo()
                        }
                    }
                })

            }
        }
    }
}