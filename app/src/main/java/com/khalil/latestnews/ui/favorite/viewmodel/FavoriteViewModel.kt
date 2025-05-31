package com.khalil.latestnews.ui.favorite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khalil.latestnews.doman.model.Article
import com.khalil.latestnews.doman.usecases.FetchNewsUseCase
import com.khalil.latestnews.doman.usecases.GetFavoritesUseCase
import com.khalil.latestnews.doman.usecases.IsArticleFavoriteUseCase
import com.khalil.latestnews.doman.usecases.ToggleFavoriteUseCase
import com.khalil.paymobtask.utils.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val isArticleFavoriteUseCase: IsArticleFavoriteUseCase
) : ViewModel() {

    val favorites: StateFlow<List<Article>> = getFavoritesUseCase()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> get() = _isFavorite


    fun toggleFavorite(article: Article) {
        viewModelScope.launch {
            toggleFavoriteUseCase(article)
            _isFavorite.value = isArticleFavoriteUseCase(article.publishedAt)
        }
    }
}

