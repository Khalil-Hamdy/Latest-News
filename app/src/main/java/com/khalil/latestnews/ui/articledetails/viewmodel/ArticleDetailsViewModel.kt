package com.khalil.latestnews.ui.articledetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khalil.latestnews.doman.model.Article
import com.khalil.latestnews.doman.usecases.IsArticleFavoriteUseCase
import com.khalil.latestnews.doman.usecases.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ArticleDetailsViewModel @Inject constructor(
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val isArticleFavoriteUseCase: IsArticleFavoriteUseCase
) : ViewModel() {

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> get() = _isFavorite

    fun toggleFavorite(article: Article) {
        viewModelScope.launch {
            toggleFavoriteUseCase(article)
            _isFavorite.value = isArticleFavoriteUseCase(article.publishedAt)
        }
    }
}

