package com.android.pixelteam.buiduclamtest.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.pixelteam.buiduclamtest.base.BaseVM
import com.android.pixelteam.buiduclamtest.data.remote.repository.WorkoutRepository
import com.android.pixelteam.buiduclamtest.model.entity.Work
import com.android.pixelteam.buiduclamtest.ui.main.adapter.WorkoutModel
import com.android.pixelteam.buiduclamtest.utils.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val workoutRepository: WorkoutRepository
) : BaseVM() {
    private val _workoutModels = MutableLiveData<List<WorkoutModel>>()
    val workoutModels: LiveData<List<WorkoutModel>>
        get() = _workoutModels

    init {
        workoutRepository.streamWorkout().onEach {
            _workoutModels.value = it
        }.launchIn(
            viewModelScope
        )
    }

    fun fetchWorkouts() {
        viewModelScope.launch {
            workoutRepository
                .fetchWorkout()
        }
    }
}

