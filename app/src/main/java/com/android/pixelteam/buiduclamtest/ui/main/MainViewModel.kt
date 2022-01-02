package com.android.pixelteam.buiduclamtest.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.pixelteam.buiduclamtest.base.BaseVM
import com.android.pixelteam.buiduclamtest.data.remote.repository.WorkoutRepository
import com.android.pixelteam.buiduclamtest.model.entity.Work
import com.android.pixelteam.buiduclamtest.ui.main.adapter.WorkoutModel
import com.android.pixelteam.buiduclamtest.utils.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val workoutRepository: WorkoutRepository
) : BaseVM(){
    private val _workoutModels = MutableLiveData<List<WorkoutModel>>()
    val workoutModels: LiveData<List<WorkoutModel>>
        get() = _workoutModels

    fun fetchWorkouts() {
        viewModelScope(_workoutModels) {
            workoutRepository
                .fetchWorkout()
        }
    }
}

