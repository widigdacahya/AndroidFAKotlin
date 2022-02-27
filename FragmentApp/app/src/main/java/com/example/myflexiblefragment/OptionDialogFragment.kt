package com.example.myflexiblefragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OptionDialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

/*
*
* DialogFragment.
* obyek fragment sekarang merupakan obyek dialog yang akan tampil mengambang di layar.
* Seperti pada obyek modal pada platform lain, obyek DialogFragment dapat disesuaikan tampilan dan fungsinya secara spesifik.
*
* */
class OptionDialogFragment : DialogFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    /*
    * Introduce every element
    *
    * */
    private lateinit var btnChooseTeletubbies: Button
    private lateinit var btnCloseChoiceTeletubbies: Button
    private lateinit var rgOptionTeletubbies: RadioGroup
    private lateinit var rbChoiceTinkyWinky: RadioButton
    private lateinit var rbChoiceDipsy: RadioButton
    private lateinit var rbChoiceLaaLaa: RadioButton
    private lateinit var rbChoicePo: RadioButton
    private lateinit var rbChoiceJamal: RadioButton
    private var optionDialogListner: OnOptionDialogListener? = null

    interface OnOptionDialogListener {
        fun onOptionChoosen(text: String?)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btnChooseTeletubbies = view.findViewById(R.id.btn_pick_teletubbiesUI)
        btnCloseChoiceTeletubbies = view.findViewById(R.id.btn_close_teletubbiesUI)
        rgOptionTeletubbies = view.findViewById(R.id.rg_options_teletubies_memberUI)
        rbChoiceTinkyWinky = view.findViewById(R.id.rb_tingkiwinkiUI)
        rbChoiceDipsy = view.findViewById(R.id.rb_dipsyUI)
        rbChoiceLaaLaa = view.findViewById(R.id.rb_lalaUI)
        rbChoicePo = view.findViewById(R.id.rb_poUI)
        rbChoiceJamal = view.findViewById(R.id.rb_jamalUI)


        btnChooseTeletubbies.setOnClickListener {
            val checkedRadioButtonId = rgOptionTeletubbies.checkedRadioButtonId

            if (checkedRadioButtonId != -1) {
                var personel: String? = when (checkedRadioButtonId) {
                    R.id.rb_tingkiwinkiUI -> rbChoiceTinkyWinky.text.toString().trim()
                    R.id.rb_dipsyUI -> rbChoiceDipsy.text.toString().trim()
                    R.id.rb_lalaUI -> rbChoiceLaaLaa.text.toString().trim()
                    R.id.rb_poUI -> rbChoicePo.text.toString().trim()
                    R.id.rb_jamalUI -> rbChoiceJamal.text.toString().trim()
                    else -> null
                }
                optionDialogListner?.onOptionChoosen(personel)
                dialog?.dismiss()
            }

        }

        btnCloseChoiceTeletubbies.setOnClickListener {
            dialog?.cancel()
        }





    }

       /*
       * untuk mengelola optionDialogListener
       * ketika fragment dipanggil dan dimatikan
       * */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        val fragment = parentFragment

        if(fragment is DetailCategoryFragment) {
            this.optionDialogListner = fragment.optionDialogListener
        }
    }

    override fun onDetach() {
        super.onDetach()
        this.optionDialogListner = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_option_dialog, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OptionDialogFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OptionDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}