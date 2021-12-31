package com.android.pixelteam.buiduclamtest.data.remote.api

import com.android.pixelteam.buiduclamtest.model.entity.Work
import com.android.pixelteam.buiduclamtest.model.response.BaseResponse
import retrofit2.http.GET

interface ApiService {
    //https://demo2187508.mockable.io/workouts
    @GET("workouts")
    suspend fun fetchWorkouts(): BaseResponse<List<Work>>
}