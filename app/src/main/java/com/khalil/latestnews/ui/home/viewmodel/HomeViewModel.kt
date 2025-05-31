package com.khalil.latestnews.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khalil.latestnews.doman.model.Article
import com.khalil.latestnews.doman.usecases.FetchNewsUseCase
import com.khalil.latestnews.doman.usecases.IsArticleFavoriteUseCase
import com.khalil.latestnews.doman.usecases.ToggleFavoriteUseCase
import com.khalil.paymobtask.utils.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchNewsUseCase: FetchNewsUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val isArticleFavoriteUseCase: IsArticleFavoriteUseCase
) : ViewModel() {

    private val _newsState = MutableStateFlow<ApiState<List<Article>>>(ApiState.Loading)
    val newsState: StateFlow<ApiState<List<Article>>> = _newsState

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> get() = _isFavorite

    init {
        fetchNews()
    }

    fun fetchNews() {
        viewModelScope.launch {
            fetchNewsUseCase().collect {
                _newsState.value = it
            }
        }
    }

    fun toggleFavorite(article: Article) {
        viewModelScope.launch {
            toggleFavoriteUseCase(article)
            _isFavorite.value = isArticleFavoriteUseCase(article.publishedAt)
            fetchNews()
        }
    }
}

