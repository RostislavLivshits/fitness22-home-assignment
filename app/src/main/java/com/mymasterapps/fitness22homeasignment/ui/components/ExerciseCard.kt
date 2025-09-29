package com.mymasterapps.fitness22homeasignment.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale
import com.mymasterapps.fitness22homeasignment.domain.model.Exercise
import com.mymasterapps.fitness22homeasignment.ui.util.asset

@Composable
fun ExerciseCard(
    ex: Exercise,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = asset(ex.thumbAsset),
                contentDescription = ex.name,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(Modifier.width(12.dp))

            Column(Modifier.weight(1f)) {
                Text(
                    text = ex.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                val weight = ex.weightLbs?.let { " x ${it} lb" } ?: ""
                Text(
                    text = "${ex.sets} sets x ${ex.repRange}$weight",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(Modifier.width(8.dp))

            AsyncImage(
                model = asset(ex.muscleIconAsset),
                contentDescription = ex.muscleGroup,
                modifier = Modifier.size(40.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}