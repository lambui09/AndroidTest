package com.android.pixelteam.buiduclamtest.data.remote.repository

import DataResult
import com.android.pixelteam.buiduclamtest.base.BaseRepository
import com.android.pixelteam.buiduclamtest.data.remote.api.ApiService
import com.android.pixelteam.buiduclamtest.model.entity.Work
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class WorkoutRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : WorkoutRepository, BaseRepository(Dispatchers.IO) {

    override suspend fun fetchWorkout(): DataResult<List<Work>> {
        return withResultContext {
            apiService.fetchWorkouts().data
        }
    }
}

interface WorkoutRepository {
    suspend fun fetchWorkout(): DataResult<List<Work>>
}