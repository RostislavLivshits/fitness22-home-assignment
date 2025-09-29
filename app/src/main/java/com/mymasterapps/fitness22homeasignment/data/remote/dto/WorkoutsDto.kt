package com.mymasterapps.fitness22homeasignment.data.remote.dto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WorkoutsFileDto(
    val workouts: List<WorkoutDayDto>
)

@JsonClass(generateAdapter = true)
data class WorkoutDayDto(
    val day: Int,
    val workout: List<ExerciseDto>
)

@JsonClass(generateAdapter = true)
data class ExerciseDto(
    @Json(name = "exercise_id") val exerciseId: Int,
    @Json(name = "exercise_name") val exerciseName: String,
    @Json(name = "exercise_thumbnail") val exerciseThumbnail: String,
    @Json(name = "muscle_group") val muscleGroup: String,
    @Json(name = "muscle_group_image") val muscleGroupImage: String,
    @Json(name = "amount_of_sets") val amountOfSets: Int,
    @Json(name = "rep_range") val repRange: String,
    @Json(name = "weight_amount") val weightAmount: String?
)