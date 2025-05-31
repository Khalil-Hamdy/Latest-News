package com.khalil.paymobtask.di

import com.khalil.latestnews.data.repoimpl.NewsRepositoryImpl
import com.khalil.latestnews.doman.repo.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMovieRepository(
        movieRepositoryImp: NewsRepositoryImpl
    ): NewsRepository
}