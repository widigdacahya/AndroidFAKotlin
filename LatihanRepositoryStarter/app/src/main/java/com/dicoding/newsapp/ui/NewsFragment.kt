package com.dicoding.newsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.newsapp.data.Result
import com.dicoding.newsapp.databinding.FragmentNewsBinding

class NewsFragment : Fragment() {

    private var tabName: String? = null

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentNewsBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tabName = arguments?.getString(ARG_TAB)

        /**
         * STEP 6
         * menginisialisasi ViewModel dan menampilkan data
         * sesuai dengan status yang didapatkan
         *
         * */
        //6_1
        val factory: ViewModelFactory = ViewModelFactory.getInstance(requireActivity())
        val viewModel: NewsViewModel by viewModels {
            factory
        }
        //6_1

        val newsAdapter = NewsAdapter()

        //6_2

        if(tabName == TAB_NEWS) {
            viewModel.getHeadlineNews().observe(viewLifecycleOwner, { result ->

                if(result != null) {
                    when(result) {
                        is Result.Loading -> {
                            binding?.progressBar?.visibility = View.VISIBLE
                        }
                        is Result.Success -> {
                            binding?.progressBar?.visibility = View.GONE
                            val newsData = result.data
                            newsAdapter.submitList(newsData)
                        }
                        is Result.Error -> {
                            binding?.progressBar?.visibility = View.GONE
                            Toast.makeText(
                                context,
                                "Terjadi Kesalahan" + result.error,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }

            })
        }

        //6_2




        
        binding?.rvNews?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = newsAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val ARG_TAB = "tab_name"
        const val TAB_NEWS = "news"
        const val TAB_BOOKMARK = "bookmark"
    }
}