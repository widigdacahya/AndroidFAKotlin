package com.cahyadesthian.secondsubmissionchy.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cahyadesthian.secondsubmissionchy.R
import com.cahyadesthian.secondsubmissionchy.databinding.FragmentFollowBinding
import com.cahyadesthian.secondsubmissionchy.ui.DetailUserActivity
import com.cahyadesthian.secondsubmissionchy.ui.adapter.UserMainAdapter
import com.cahyadesthian.secondsubmissionchy.viewmodel.FollowerViewMode

class FollowerFragment : Fragment(R.layout.fragment_follow) {

    private var _binding : FragmentFollowBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel : FollowerViewMode
    private lateinit var adapter: UserMainAdapter
    private lateinit var username: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        username = args?.getString(DetailUserActivity.EXTRA_USERNAME).toString()

        _binding = FragmentFollowBinding.bind(view)


        adapter= UserMainAdapter()
        adapter.notifyDataSetChanged()

        binding.apply {
            rvFollowerFragUI.setHasFixedSize(true)
            rvFollowerFragUI.layoutManager = LinearLayoutManager(activity)
            rvFollowerFragUI.adapter = adapter
        }

        showLoading(true)
        viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(FollowerViewMode::class.java)
        viewModel.setListFollowers(username)
        viewModel.getListFollower().observe(viewLifecycleOwner,{
            if(it!= null) {
                adapter.setList(it)
                showLoading(false)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading(condition: Boolean) {
        if(condition) binding.pbFragUI.visibility = View.VISIBLE else binding.pbFragUI.visibility = View.GONE
    }


}