

package com.example.nutribite.di

import android.content.Context
import com.example.nutribite.data.repository.HomeRepositoryImpl
import com.example.nutribite.data.repository.LoginRepositoryImpl
import com.example.nutribite.data.repository.UserDataRepositoryImpl
import com.example.nutribite.domain.repository.HomeRepository
import com.example.nutribite.domain.repository.LoginRepository
import com.example.nutribite.domain.repository.UserDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLoginRepository(
        @ApplicationContext context: Context
    ): LoginRepository = LoginRepositoryImpl(context = context)

    @Provides
    @Singleton
    fun providesHomeRepository(): HomeRepository = HomeRepositoryImpl()

    @Provides
    @Singleton
    fun providesUserDataRepository(): UserDataRepository = UserDataRepositoryImpl()




}