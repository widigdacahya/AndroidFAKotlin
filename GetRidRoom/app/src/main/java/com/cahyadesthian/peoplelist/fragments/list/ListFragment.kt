package com.cahyadesthian.peoplelist.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cahyadesthian.peoplelist.R
import com.cahyadesthian.peoplelist.viewmodel.UserViewModel
import com.cahyadesthian.peoplelist.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private lateinit var listBinding: FragmentListBinding
    private lateinit var mUserViewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //val view = inflater.inflate(R.layout.fragment_list, container, false)
        listBinding = FragmentListBinding.inflate(inflater,container,false)
        listBinding.fabAdddFragList.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }


        //RecyclerView
        val listAdapter = ListAdapter()
        listBinding.apply {
            rvListuserFragList.adapter = listAdapter
            rvListuserFragList.layoutManager = LinearLayoutManager(requireContext())
        }

        //UserViewModel
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java )
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            listAdapter.setData(user)
        })

        return listBinding.root
    }


}