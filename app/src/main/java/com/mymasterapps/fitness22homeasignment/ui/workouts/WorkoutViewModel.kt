package com.mymasterapps.fitness22homeasignment.ui.workouts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.mymasterapps.fitness22homeasignment.data.repo.WorkoutRepository
import com.mymasterapps.fitness22homeasignment.domain.model.WorkoutDay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WorkoutViewModel(app: Application) : AndroidViewModel(app) {

    private val repo = WorkoutRepository(app)

    private val _day = MutableStateFlow<WorkoutDay?>(null)
    val day: StateFlow<WorkoutDay?> = _day

    fun load(dayIndex: Int) {
        viewModelScope.launch {
            _day.value = repo.loadDay(dayIndex)
        }
    }
}