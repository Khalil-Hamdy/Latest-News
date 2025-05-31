package com.khalil.paymobtask.di

import android.content.Context
import androidx.room.Room
import com.khalil.latestnews.data.datasource.local.dao.FavoriteNewsDao
import com.khalil.latestnews.data.datasource.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "movie_db"
        ).build()
    }

    @Provides
    fun provideMovieDao(database: AppDatabase): FavoriteNewsDao {
        return database.favoriteDao()
    }
}