package com.cahyadesthian.peoplelist.fragments.update

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.cahyadesthian.peoplelist.R
import com.cahyadesthian.peoplelist.databinding.FragmentUpdateBinding


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var updateFragBinding: FragmentUpdateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_update, container, false)

        updateFragBinding = FragmentUpdateBinding.inflate(inflater,container,false)

        updateFragBinding.edtFirstnameFragUpdt.text = Editable.Factory.getInstance().newEditable(args.currentUser.firstName)
        updateFragBinding.edtLastnameFragAdd.setText(args.currentUser.lastName)
        updateFragBinding.edtAgeFragUpdt.setText(args.currentUser.age.toString())


        return updateFragBinding.root


    }


}