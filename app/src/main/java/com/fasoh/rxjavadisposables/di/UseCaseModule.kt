package com.fasoh.rxjavadisposables.di

import com.fasoh.rxjavadisposables.repositories.MainRepository
import com.fasoh.rxjavadisposables.usecases.MainUseCase
import com.fasoh.rxjavadisposables.usecases.MainUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun provideMainUseCase(mainRepository: MainRepository): MainUseCase {
        return MainUseCaseImpl(mainRepository)
    }
}