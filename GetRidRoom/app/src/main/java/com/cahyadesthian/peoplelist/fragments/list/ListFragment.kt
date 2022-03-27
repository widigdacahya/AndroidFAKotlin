package com.cahyadesthian.peoplelist.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.cahyadesthian.peoplelist.R
import com.cahyadesthian.peoplelist.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private lateinit var listBinding: FragmentListBinding


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

        return listBinding.root
    }


}