package com.android.pixelteam.buiduclamtest.di

import com.android.pixelteam.buiduclamtest.data.remote.api.ApiService
import com.android.pixelteam.buiduclamtest.data.remote.repository.WorkoutRepository
import com.android.pixelteam.buiduclamtest.data.remote.repository.WorkoutRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideWorkoutRepository(
        apiService: ApiService
    ): WorkoutRepository {
        return WorkoutRepositoryImpl(
            apiService
        )
    }
}