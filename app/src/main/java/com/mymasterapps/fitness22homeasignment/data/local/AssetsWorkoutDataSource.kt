package com.mymasterapps.fitness22homeasignment.data.local

import android.content.Context
import com.mymasterapps.fitness22homeasignment.data.remote.dto.WorkoutsFileDto
import com.squareup.moshi.Moshi

class AssetsWorkoutDataSource(private val context: Context) {

    private val moshi: Moshi = Moshi.Builder().build()

    fun load(): WorkoutsFileDto {
        val json = context.assets.open("workouts.json")
            .bufferedReader().use { it.readText() }

        val adapter = moshi.adapter(WorkoutsFileDto::class.java)
        return requireNotNull(adapter.fromJson(json)) { "Invalid workouts.json" }
    }
}