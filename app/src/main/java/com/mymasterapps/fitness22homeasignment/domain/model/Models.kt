package com.mymasterapps.fitness22homeasignment.domain.model

data class Exercise(
    val id: Int,
    val name: String,
    val thumbAsset: String,
    val muscleGroup: String,
    val muscleIconAsset: String,
    val sets: Int,
    val repRange: String,
    val weightLbs: Double?
)

data class WorkoutDay(
    val day: Int,
    val exercises: List<Exercise>
)