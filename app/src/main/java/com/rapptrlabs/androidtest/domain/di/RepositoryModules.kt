package com.rapptrlabs.androidtest.domain.di

import com.rapptrlabs.androidtest.data.repository.ChatMessageRepositoryImpl
import com.rapptrlabs.androidtest.data.repository.LoginRepositoryImpl
import com.rapptrlabs.androidtest.domain.repository.ChatMessageRepository
import com.rapptrlabs.androidtest.domain.repository.LoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModules {
    @Binds
    fun providePointsRepository(repository: ChatMessageRepositoryImpl): ChatMessageRepository

    @Binds
    fun provideLoginRepository(repository: LoginRepositoryImpl): LoginRepository
}