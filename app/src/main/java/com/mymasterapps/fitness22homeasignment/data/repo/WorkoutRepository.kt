package com.mymasterapps.fitness22homeasignment.data.repo

import android.content.Context
import com.mymasterapps.fitness22homeasignment.data.local.AssetsWorkoutDataSource
import com.mymasterapps.fitness22homeasignment.domain.mapper.toDomain
import com.mymasterapps.fitness22homeasignment.domain.model.WorkoutDay

class WorkoutRepository(context: Context) {
    private val ds = AssetsWorkoutDataSource(context)

    fun loadAllDays(): List<WorkoutDay> = ds.load().toDomain()

    fun loadDay(day: Int): WorkoutDay? =
        loadAllDays().firstOrNull { it.day == day }
}