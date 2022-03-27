package com.cahyadesthian.peoplelist.fragments.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.cahyadesthian.peoplelist.R
import com.cahyadesthian.peoplelist.data.User
import com.cahyadesthian.peoplelist.data.UserViewModel
import com.cahyadesthian.peoplelist.databinding.FragmentAddBinding



class AddFragment : Fragment() {

    private lateinit var addBinding: FragmentAddBinding

    private lateinit var mUserViewModel : UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_add, container, false)

        addBinding = FragmentAddBinding.inflate(inflater,container,false)

        addBinding.btnAddFragAdd.setOnClickListener{
            insertDataToDatabase()
        }

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        return addBinding.root
    }

    private fun insertDataToDatabase() {
        var firstName : String
        var lastName: String
        var age : String

        if(
            addBinding.edtFirstnameFragAdd.text.isNotEmpty() &&
            addBinding.edtLastnameFragAdd.text.isNotEmpty() &&
            addBinding.edtAgeFragAdd.text.isNotEmpty()
        ) {
            addBinding.apply {
                firstName = edtFirstnameFragAdd.text.toString()
                lastName = edtLastnameFragAdd.text.toString()
                age = edtAgeFragAdd.text.toString()

                //Room library will know that id is primary key
                //so its oke now we pass 0
                val user = User(0,firstName,lastName,age.toInt())

                //add data to database
                mUserViewModel.addUser(user)
                Toast.makeText(requireContext(),"User added succesfully", Toast.LENGTH_SHORT).show()

                //navigate back
                findNavController().navigate(R.id.action_addFragment_to_listFragment)
            }
        } else {
            Toast.makeText(requireContext(),"All field need to be filled", Toast.LENGTH_SHORT).show()

        }

    }

}