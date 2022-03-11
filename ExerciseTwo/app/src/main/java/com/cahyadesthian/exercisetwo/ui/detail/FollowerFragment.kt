package com.cahyadesthian.exercisetwo.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cahyadesthian.exercisetwo.R
import com.cahyadesthian.exercisetwo.databinding.FragmentFollowBinding
import com.cahyadesthian.exercisetwo.ui.main.UserAdapter

class FollowerFragment : Fragment(R.layout.fragment_follow) {

    private var _binding : FragmentFollowBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: FollowersViewModel
    private lateinit var adapter: UserAdapter

    private lateinit var username: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //argument diatas binding biar bisa muncul hasilnya
        val args = arguments
        username = args?.getString(DetailUserActivity.EXTRA_USERNAME).toString()


        _binding = FragmentFollowBinding.bind(view)


        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        binding.apply {
            rvFollowFragUI.setHasFixedSize(true)
            rvFollowFragUI.layoutManager = LinearLayoutManager(activity)
            rvFollowFragUI.adapter = adapter
        }


        showLoading(true)
        viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(FollowersViewModel::class.java)
        viewModel.setListFollowers(username)
        viewModel.getListFollwoer().observe(viewLifecycleOwner, {
            if(it!=null) {
                adapter.setList(it)
                showLoading(false)
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun showLoading(state: Boolean) {
        if(state) binding.pbFollowFragUI.visibility = View.VISIBLE else binding.pbFollowFragUI.visibility = View.GONE
    }

}