package com.example.thenavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.thenavigation.databinding.FragmentDetailCategoryBinding

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailCategoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailCategoryFragment : Fragment() {

    private var _detailCtgrBinding : FragmentDetailCategoryBinding? = null
    private val detailCtgrBinding get() = _detailCtgrBinding!!


    private var param1: String? = null
    private var param2: String? = null

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
        //return inflater.inflate(R.layout.fragment_detail_category, container, false)

        _detailCtgrBinding = FragmentDetailCategoryBinding.inflate(inflater,container,false)
        val view = detailCtgrBinding.root
        return view
    }

    //[]
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //[cara Bundle]
        //val dataName = arguments?.getString(CategoryFragment.EXTRA_NAME)
        //val dataDesc = arguments?.getLong(CategoryFragment.EXTRA_STOCK)

        //detailCtgrBinding.tvCategorynameFragUI.text = dataName
        //detailCtgrBinding.tvCategorydescFragUI.text = "Stock : $dataDesc"


        //[CARA SAFEARGS]
        val dataName = DetailCategoryFragmentArgs.fromBundle(arguments as Bundle).name
        val dataDesc = DetailCategoryFragmentArgs.fromBundle(arguments as Bundle).stock

        detailCtgrBinding.tvCategorynameFragUI.text = dataName
        detailCtgrBinding.tvCategorydescFragUI.text = "Stock left : $dataDesc"

        detailCtgrBinding.btnGotoHomeFragUI.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_detailCategoryFragment_to_homeFragment)
        )


    }

    //[]
    override fun onDestroy() {
        super.onDestroy()
        _detailCtgrBinding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailCategoryFragment.
         */

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailCategoryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}