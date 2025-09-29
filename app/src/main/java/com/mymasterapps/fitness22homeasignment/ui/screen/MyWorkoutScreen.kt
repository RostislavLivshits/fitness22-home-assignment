package com.mymasterapps.fitness22homeasignment.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mymasterapps.fitness22homeasignment.ui.components.ExerciseCard
import com.mymasterapps.fitness22homeasignment.ui.workouts.WorkoutViewModel

@Composable
fun MyWorkoutScreen(vm: WorkoutViewModel = viewModel()) {
    val day by vm.day.collectAsState()

    // Доступные дни. Для простоты можно получить из репо позже, а пока — список:
    var selectedDay by remember { mutableStateOf(1) }
    val availableDays = listOf(1, 3, 4)

    LaunchedEffect(selectedDay) { vm.load(selectedDay) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        // Заголовок
        Text(
            text = "My Workout",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
        )

        // Чипы дней
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

        // Сводка (кол-во упражнений / время / калории)
        val count = day?.exercises?.size ?: 0
        SummaryBar(
            count = count,
            minutes = estimateMinutes(count), // простая оценка
            calories = estimateCalories(count)
        )

        Spacer(Modifier.height(8.dp))

        // Список
        LazyColumn(
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            val list = day?.exercises
            if (list == null) {
                item { Text("Loading…", modifier = Modifier.padding(16.dp)) }
            } else {
                items(list, key = { it.id }) { ex ->
                    ExerciseCard(ex = ex)
                }
            }
        }
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

// грубые оценки, чтобы заполнить сводку (можно заменить реальными правилами)
private fun estimateMinutes(count: Int) = (count * 8.5).toInt().coerceAtLeast(10)
private fun estimateCalories(count: Int) = (count * 40).coerceAtLeast(120)