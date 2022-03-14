package com.cahyadesthian.githubuserchy.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.cahyadesthian.githubuserchy.R
import com.cahyadesthian.githubuserchy.databinding.FragmentFollowBinding
import com.cahyadesthian.githubuserchy.model.UserItemsResponse
import com.cahyadesthian.githubuserchy.ui.DetailUserActivity
import com.cahyadesthian.githubuserchy.ui.adapter.UserGridRecyclerViewAdapter
import com.cahyadesthian.githubuserchy.viewmodel.FollowingViewModel

class FollowingFragment : Fragment(R.layout.fragment_follow) {

    private var _binding : FragmentFollowBinding? = null
    private val binding get() = _binding!!

    private lateinit var username: String
    private lateinit var viewModel : FollowingViewModel
    private lateinit var adapter: UserGridRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFollowBinding.bind(view)
        loadingIndicator(true)
        val args = arguments
        username = args?.getString(DetailUserActivity.EXTRA_USERNAME).toString()
        adapter = UserGridRecyclerViewAdapter()
        adapter.setOnItemClickCallback(object : UserGridRecyclerViewAdapter.OnItemClickCallback {
            override fun onItemClicked(user: UserItemsResponse) {
                Intent(requireContext(), DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME, user.login)
                    startActivity(it)
                }
            }

        })
        adapter.notifyDataSetChanged()

        viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(FollowingViewModel::class.java)

        viewModel.setListFollowing(username)
        viewModel.getListFollowing().observe(viewLifecycleOwner, {
            if(it!=null) {
                adapter.setList(it)
                loadingIndicator(false)
            }
        })

        binding.apply {
            rvFollowFragUI.setHasFixedSize(true)
            rvFollowFragUI.layoutManager = GridLayoutManager(requireContext(),2)
            rvFollowFragUI.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadingIndicator(isLoading: Boolean) {
        if(isLoading) binding.pbFragUI.visibility = View.VISIBLE else binding.pbFragUI.visibility = View.INVISIBLE
    }
}