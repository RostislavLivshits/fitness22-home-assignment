package com.mymasterapps.fitness22homeasignment.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = Yellow,
    onPrimary = TextPrimary,
    secondary = TextSecondary,
    background = BackgroundLight,
    surface = SurfaceLight,
    surfaceVariant = TextSecondary,
    onSurface = TextPrimary,
    onBackground = TextPrimary,
)

private val DarkColorScheme = darkColorScheme(
    primary = YellowDark,
    onPrimary = Color.Black,
    secondary = TextMuted,
    background = BackgroundDark,
    surface = SurfaceDark,
    onSurface = Color.White,
    surfaceVariant = TextMuted,
    onBackground = Color.White,
)

@Composable
fun Fitness22HomeAsignmentTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
