package com.android.pixelteam.buiduclamtest.data.remote.repository

import com.android.pixelteam.buiduclamtest.base.BaseRepository
import com.android.pixelteam.buiduclamtest.data.remote.api.ApiService
import com.android.pixelteam.buiduclamtest.di.IoDispatcher
import com.android.pixelteam.buiduclamtest.model.entity.Work
import com.android.pixelteam.buiduclamtest.utils.DataResult
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class WorkoutRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher
) : WorkoutRepository, BaseRepository(coroutineDispatcher) {

    override suspend fun fetchWorkout(): DataResult<List<Work>> {
        return withResultContext {
            apiService.fetchWorkouts().data
        }
    }
}

interface WorkoutRepository {
    suspend fun fetchWorkout(): DataResult<List<Work>>
}