package com.khalil.latestnews.ui.favorite.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.khalil.latestnews.databinding.FragmentFavoriteBinding
import com.khalil.latestnews.ui.favorite.viewmodel.FavoriteViewModel
import com.khalil.latestnews.ui.home.adapter.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val favoriteViewModel: FavoriteViewModel by viewModels()
    private lateinit var newsAdapter: NewsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        observeState()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

    private fun setupAdapter() {
        newsAdapter = NewsAdapter(
            onFavoriteClick = { article ->
                favoriteViewModel.toggleFavorite(article)
                newsAdapter.notifyDataSetChanged()
            },
            onNewsClick = { article ->
                val direction = FavoriteFragmentDirections
                    .actionFavoriteFragmentToArticleDetailsFragment(article)
                findNavController().navigate(direction)
            }
        )
        binding.recyclerNews.adapter = newsAdapter
        binding.recyclerNews.layoutManager = LinearLayoutManager(requireContext())

    }

    private fun observeState() {
        lifecycleScope.launchWhenStarted {
            favoriteViewModel.favorites.collect { articles ->
                newsAdapter.submitList(articles)
                binding.emptyStateView.isVisible = articles.isEmpty()
            }
        }
    }


}