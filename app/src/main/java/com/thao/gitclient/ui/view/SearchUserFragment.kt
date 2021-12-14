package com.thao.gitclient.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.thao.gitclient.R
import androidx.fragment.app.activityViewModels
import com.thao.gitclient.databinding.SearchUserFragmentBinding
import com.thao.gitclient.ui.viewmodel.UserViewModel

class SearchUserFragment: Fragment() {
    lateinit var binding: SearchUserFragmentBinding
    private val viewModel: UserViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SearchUserFragmentBinding.inflate(inflater,container,false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getUserQuery();
        binding.tvName.setOnClickListener {
            (activity as MainActivity).addFragmentView(R.id.myNavHostFragment, UserProfileFragment::class.java.name, null)
         }
        updateUserUI()
    }

    private fun getUserQuery() {
        binding.searchUser.setOnQueryTextListener(object :  SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.getGitHubUserProfile(query)
                return false
            }
        })
    }

    private fun updateUserUI() {
        viewModel.userLiveData.observe(this) { response ->
            if (response == null) {
                binding.user = null
                binding.tvNoUser.visibility = View.VISIBLE
                binding.layoutUserProfile.visibility = View.GONE
            } else {
                binding.layoutUserProfile.visibility = View.VISIBLE
                binding.tvNoUser.visibility = View.GONE
                binding.user = response
                Glide.with(this).load(response.avatar_url).into(binding.userAvatar);
            }
        }
    }
}