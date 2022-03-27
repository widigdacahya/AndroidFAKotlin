package com.cahyadesthian.peoplelist.fragments.update

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.cahyadesthian.peoplelist.R
import com.cahyadesthian.peoplelist.databinding.FragmentUpdateBinding
import com.cahyadesthian.peoplelist.model.User
import com.cahyadesthian.peoplelist.viewmodel.UserViewModel


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var updateFragBinding: FragmentUpdateBinding

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_update, container, false)

        updateFragBinding = FragmentUpdateBinding.inflate(inflater,container,false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)


        updateFragBinding.edtFirstnameFragUpdt.text = Editable.Factory.getInstance().newEditable(args.currentUser.firstName)
        updateFragBinding.edtLastnameFragAdd.setText(args.currentUser.lastName)
        updateFragBinding.edtAgeFragUpdt.setText(args.currentUser.age.toString())

        updateFragBinding.btnUpdateFragUpdt.setOnClickListener {
            updateItem()
        }
        return updateFragBinding.root


    }


    private fun updateItem() {

        if(
            updateFragBinding.edtFirstnameFragUpdt.text.isNotEmpty() &&
            updateFragBinding.edtLastnameFragAdd.text.isNotEmpty() &&
            updateFragBinding.edtAgeFragUpdt.text.isNotEmpty()
        ) {

            val firstNameUpdate = updateFragBinding.edtFirstnameFragUpdt.text.toString()
            val lastNameUpdate = updateFragBinding.edtLastnameFragAdd.text.toString()
            val ageUpdate = updateFragBinding.edtAgeFragUpdt.text.toString().toInt()

            //craete updated user
            val updatedUser = User(args.currentUser.id,firstNameUpdate,lastNameUpdate,ageUpdate)

            //update current user
            mUserViewModel.updateUser(updatedUser)

            Toast.makeText(requireContext(), "User Updated Succesfully", Toast.LENGTH_SHORT).show()


            //navigate back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)


        }else {
            Toast.makeText(requireContext(), "Yow, don't leave any single input empty", Toast.LENGTH_SHORT).show()

        }

    }

}