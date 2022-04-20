package com.cahyadesthian.learnexercisethree.ui.fragment
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cahyadesthian.learnexercisethree.R
import com.cahyadesthian.learnexercisethree.data.model.UserItemResponse
import com.cahyadesthian.learnexercisethree.databinding.FragmentFollowBinding
import com.cahyadesthian.learnexercisethree.ui.activity.DetailUserActivity
import com.cahyadesthian.learnexercisethree.ui.adapter.UserRowRecyclerViewAdapter
import com.cahyadesthian.learnexercisethree.viewmodel.FollowingrViewModel



/**
 * For show list of user's
 * following in detail user
 * it would be work with tab and view pager
 * */

class FollowingFragment: Fragment(R.layout.fragment_follow) {

    private var _binding : FragmentFollowBinding?= null
    private val binding get() = _binding!!

    private lateinit var username: String
    private lateinit var followingViewModel : FollowingrViewModel
    private lateinit var rowRecyclerViewAdapter: UserRowRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentFollowBinding.bind(view)
        loadingIndicator(true)

        val args = arguments
        username = args?.getString(DetailUserActivity.EXTRA_USERNAME).toString()
        rowRecyclerViewAdapter = UserRowRecyclerViewAdapter()

        rowRecyclerViewAdapter.setOnItemClickCallback(object : UserRowRecyclerViewAdapter.OnItemClickCallback {
            override fun onItemClicked(user: UserItemResponse) {
                Intent(requireContext(), DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME, user.login)
                    it.putExtra(DetailUserActivity.EXTRA_ID,user.id)
                    it.putExtra(DetailUserActivity.EXTRA_URL, user.avatar_url)
                    startActivity(it)
                }
            }

        })


        followingViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowingrViewModel::class.java)

        followingViewModel.setListFollowing(username)
        followingViewModel.getListFollowing().observe(viewLifecycleOwner, {

            if(it!=null) {
                rowRecyclerViewAdapter.setData(it)
                loadingIndicator(false)
            }

        })

        binding.apply {
            rvFollowFragUI.setHasFixedSize(true)
            rvFollowFragUI.layoutManager = LinearLayoutManager(requireContext())
            rvFollowFragUI.adapter = rowRecyclerViewAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun loadingIndicator(isLoading: Boolean) {
        if(isLoading) binding.pbFragUI.visibility = View.VISIBLE else binding.pbFragUI.visibility = View.INVISIBLE
    }
}