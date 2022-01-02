package com.android.pixelteam.buiduclamtest.data.remote.repository

import com.android.pixelteam.buiduclamtest.base.BaseRepository
import com.android.pixelteam.buiduclamtest.data.remote.api.ApiService
import com.android.pixelteam.buiduclamtest.di.IoDispatcher
import com.android.pixelteam.buiduclamtest.model.entity.Work
import com.android.pixelteam.buiduclamtest.ui.main.adapter.WorkoutModel
import com.android.pixelteam.buiduclamtest.utils.DataResult
import com.android.pixelteam.buiduclamtest.utils.enum.Common.mapDate
import com.android.pixelteam.buiduclamtest.utils.enum.Common.mapDayOfMonth
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class WorkoutRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher
) : WorkoutRepository, BaseRepository(coroutineDispatcher) {

    override suspend fun fetchWorkout(): DataResult<List<WorkoutModel>> = withResultContext {
        val dayWithAssignments: List<Work> =
            apiService.fetchWorkouts().data.sortedBy(Work::numberDay)

        val items: List<MutableList<WorkoutModel>> = dayWithAssignments.map { dayInfo ->
            val workoutModels = mutableListOf<WorkoutModel>()
            for (assignment in dayInfo.assignments) {
                if (workoutModels.isEmpty()) {
                    val dayOfWeekName = mapDate[dayInfo.numberDay]!!
                    val dayOfMonthNumber = mapDayOfMonth[dayInfo.numberDay]!!
                    val model = WorkoutModel.DayAndJobModel(
                        dayOfWeek = dayOfWeekName,
                        dateOfWeek = dayOfMonthNumber, assignment = assignment
                    )
                    workoutModels.add(model)
                } else {
                    val model = WorkoutModel.OnlyJobModel(assignment = assignment)
                    workoutModels.add(model)
                }
            }
            workoutModels
        }
        return@withResultContext items.flatten()
    }
}

interface WorkoutRepository {
    suspend fun fetchWorkout(): DataResult<List<WorkoutModel>>
}