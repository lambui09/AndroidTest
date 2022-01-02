package com.android.pixelteam.buiduclamtest.di

import com.android.pixelteam.buiduclamtest.data.remote.repository.WorkoutRepository
import com.android.pixelteam.buiduclamtest.data.remote.repository.WorkoutRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideWorkoutRepository(impl: WorkoutRepositoryImpl): WorkoutRepository
}