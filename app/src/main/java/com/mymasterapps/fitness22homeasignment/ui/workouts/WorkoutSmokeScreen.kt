package com.mymasterapps.fitness22homeasignment.ui.workouts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun WorkoutSmokeScreen(vm: WorkoutViewModel = viewModel()) {
    val day by vm.day.collectAsState()
    LaunchedEffect(Unit) { vm.load(1) }

    LazyColumn(Modifier.fillMaxSize()) {
        val list = day?.exercises
        if (list == null) {
            item { Text("Loading…") }
        } else {
            items(list, key = { it.id }) { ex ->
                val w = ex.weightLbs?.let { " x ${it} lb" } ?: ""
                Text("${ex.name}\n${ex.sets} sets x ${ex.repRange}$w")
                HorizontalDivider()
            }
        }
    }
}