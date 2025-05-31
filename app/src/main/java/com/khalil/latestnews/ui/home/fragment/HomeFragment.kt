package com.khalil.latestnews.ui.home.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.khalil.latestnews.R
import com.khalil.latestnews.databinding.FragmentHomeBinding
import com.khalil.latestnews.ui.home.adapter.NewsAdapter
import com.khalil.latestnews.ui.home.viewmodel.HomeViewModel
import com.khalil.paymobtask.utils.ApiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        val linearLayout =requireActivity().findViewById<LinearLayout>(R.id.linearLayoutBottom)
        linearLayout.visibility = View.VISIBLE
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        observeState()
        binding.swipeRefresh.setOnRefreshListener {
            homeViewModel.fetchNews()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

    override fun onResume() {
        super.onResume()
        homeViewModel.fetchNews()
    }

    private fun setupAdapter() {
        newsAdapter = NewsAdapter(
            onFavoriteClick = { article ->
                homeViewModel.toggleFavorite(article)
                newsAdapter.notifyDataSetChanged()
            },
            onNewsClick = { article ->
                val direction = HomeFragmentDirections
                    .actionHomeFragmentToArticleDetailsFragment(article)
                findNavController().navigate(direction)
            }
        )
        binding.recyclerNews.adapter = newsAdapter
        binding.recyclerNews.layoutManager = LinearLayoutManager(requireContext())

    }

    private fun observeState() {
        lifecycleScope.launchWhenStarted {
            homeViewModel.newsState.collect { state ->
                when (state) {
                    is ApiState.Loading -> {
                        binding.progressBar.isVisible = true
                        binding.emptyStateView.isVisible = false
                    }

                    is ApiState.Success -> {
                        binding.progressBar.isVisible = false
                        binding.emptyStateView.isVisible = false
                        binding.swipeRefresh.isRefreshing = false
                        newsAdapter.submitList(state.data)
                    }

                    is ApiState.Error -> {
                        binding.progressBar.isVisible = false
                        binding.swipeRefresh.isRefreshing = false
                        Toast.makeText(
                            requireContext(),
                            state.message ?: "Something went wrong",
                            Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

}