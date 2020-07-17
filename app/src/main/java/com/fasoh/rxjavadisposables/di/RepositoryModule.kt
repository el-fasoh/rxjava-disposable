package com.fasoh.rxjavadisposables.di

import com.fasoh.rxjavadisposables.FxApi
import com.fasoh.rxjavadisposables.repositories.MainRepository
import com.fasoh.rxjavadisposables.repositories.MainRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideMainRepository(fxApi: FxApi): MainRepository{
        return MainRepositoryImpl(fxApi)
    }
}