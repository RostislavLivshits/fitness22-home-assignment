package com.mymasterapps.fitness22homeasignment.domain.mapper

import com.mymasterapps.fitness22homeasignment.data.remote.dto.ExerciseDto
import com.mymasterapps.fitness22homeasignment.data.remote.dto.WorkoutDayDto
import com.mymasterapps.fitness22homeasignment.data.remote.dto.WorkoutsFileDto
import com.mymasterapps.fitness22homeasignment.domain.model.Exercise
import com.mymasterapps.fitness22homeasignment.domain.model.WorkoutDay


fun WorkoutsFileDto.toDomain(): List<WorkoutDay> =
    workouts.map { it.toDomain() }

fun WorkoutDayDto.toDomain(): WorkoutDay =
    WorkoutDay(day = day, exercises = workout.map { it.toDomain() })

fun ExerciseDto.toDomain(): Exercise =
    Exercise(
        id = exerciseId,
        name = exerciseName,
        thumbAsset = exerciseThumbnail,
        muscleGroup = muscleGroup,
        muscleIconAsset = muscleGroupImage,
        sets = amountOfSets,
        repRange = repRange,
        weightLbs = weightAmount?.toDoubleOrNull()
    )