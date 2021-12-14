package com.thao.gitclient.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.thao.gitclient.ui.viewmodel.UserViewModel
import androidx.lifecycle.ViewModelProvider
import com.thao.gitclient.R
import com.thao.gitclient.Utils.Utils
import com.thao.gitclient.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var currentFragment = ""
    var mFragmentManager: FragmentManager = supportFragmentManager
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        addFragmentView(R.id.myNavHostFragment, SearchUserFragment::class.java.name, null)
    }

    fun addFragmentView(
        containerId: Int,
        fragment: String,
        bundle: Bundle?
    ) {

        var fragmentTAg = mFragmentManager.findFragmentByTag(fragment)
        if (fragmentTAg != null)
            return
        currentFragment = fragment
        mFragmentManager.beginTransaction()
            .replace(containerId, Utils.getFragment(fragment, bundle), fragment)
            .addToBackStack(fragment)
            .commit()
    }

    private fun getFragmentCount(): Int {
        return mFragmentManager.backStackEntryCount
    }

    override fun onBackPressed() {
        when {
            getFragmentCount() == 1 -> {

                this.finishAffinity()
            }
            getFragmentCount() > 1 -> {
                mFragmentManager.popBackStack()
            }
            else -> {
                super.onBackPressed()
            }
        }
    }
}