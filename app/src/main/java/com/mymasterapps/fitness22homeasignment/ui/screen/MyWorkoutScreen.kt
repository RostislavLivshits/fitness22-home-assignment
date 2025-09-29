package com.mymasterapps.fitness22homeasignment.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mymasterapps.fitness22homeasignment.ui.components.ExerciseCard
import com.mymasterapps.fitness22homeasignment.ui.workouts.WorkoutViewModel

@Composable
fun MyWorkoutScreen(vm: WorkoutViewModel = viewModel()) {
    val day by vm.day.collectAsState()

    var selectedDay by remember { mutableIntStateOf(1) }
    val availableDays = listOf(1, 3, 4)

    LaunchedEffect(selectedDay) { vm.load(selectedDay) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {

        Text(
            text = "My Workout",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
        )

        Row(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            availableDays.forEach { d ->
                FilterChip(
                    selected = selectedDay == d,
                    onClick = { selectedDay = d },
                    label = { Text("Day $d") }
                )
            }
        }

        UpcomingHeader(
            weekText = "Week 1/5 · Foundations",
            phase = "UPCOMING WORKOUT",
            workoutName = "Push"
        )

     //   val count = day?.exercises?.size ?: 0

        val list = day?.exercises ?: emptyList()
        WorkoutSection(
            count = list.size,
            minutes = estimateMinutes(list.size),
            calories = estimateCalories(list.size)
        ) {
            LazyColumn(
                contentPadding = PaddingValues(
                    start = 0.dp, end = 0.dp,
                    top = 4.dp, bottom = 96.dp // место под кнопку
                )
            ) {
                items(list, key = { it.id }) { ex ->
                    ExerciseCard(ex)
                }
            }
        }

//        SummaryBar(
//            count = count,
//            minutes = estimateMinutes(count),
//            calories = estimateCalories(count)
//        )
//
//        Spacer(Modifier.height(8.dp))
//
//
//        LazyColumn(
//            contentPadding = PaddingValues(bottom = 24.dp)
//        ) {
//            val list = day?.exercises
//            if (list == null) {
//                item { Text("Loading…", modifier = Modifier.padding(16.dp)) }
//            } else {
//                items(list, key = { it.id }) { ex ->
//                    ExerciseCard(ex = ex)
//                }
//            }
//        }
    }
}

@Composable
private fun SummaryBar(
    count: Int,
    minutes: Int,
    calories: Int
) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        tonalElevation = 1.dp,
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {
        Row(
            Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text("⚡ $count Exercises")
            Text("⏱ $minutes Min")
            Text("🔥 $calories Cal")
        }
    }
}

@Composable
fun UpcomingHeader(
    weekText: String = "Week 1/5 · Foundations",
    phase: String = "UPCOMING WORKOUT",
    workoutName: String = "Push",
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            weekText,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            phase,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            workoutName,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun WorkoutSection(
    count: Int,
    minutes: Int,
    calories: Int,
    content: @Composable ColumnScope.() -> Unit
) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        tonalElevation = 1.dp,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column(Modifier.padding(vertical = 8.dp)) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text("⚡ $count Exercises")
                Text("⏱ $minutes Min")
                Text("🔥 $calories Cal")
            }
            HorizontalDivider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f))
            Column(Modifier.padding(top = 4.dp)) {
                content()
            }
        }
    }
}

private fun estimateMinutes(count: Int) = (count * 8.5).toInt().coerceAtLeast(10)
private fun estimateCalories(count: Int) = (count * 40).coerceAtLeast(120)

