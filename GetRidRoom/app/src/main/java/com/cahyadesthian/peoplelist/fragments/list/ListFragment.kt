package com.cahyadesthian.peoplelist.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
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


        //Add Menu
        setHasOptionsMenu(true)

        return listBinding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete) {
            deleteAllUsers()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun deleteAllUsers() {
        val builder = AlertDialog.Builder(requireContext())

        builder.setPositiveButton("Yes") { _, _ ->
            mUserViewModel.deleteAllUser()
            Toast.makeText(requireContext(), "All user deleted", Toast.LENGTH_SHORT).show()


        }

        builder.setNegativeButton("No") { _, _ ->

        }

        builder.setTitle("Delete All User")
        builder.setMessage("Are you sure that you want to delete All User?")
        builder.create().show()
    }




}