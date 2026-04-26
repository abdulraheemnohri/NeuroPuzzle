package com.neuropuzzle.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

enum class PuzzleTheme {
    NEON, MINIMAL, RETRO
}

private val NeonColorScheme = darkColorScheme(
    primary = NeonBlue,
    secondary = NeonPurple,
    tertiary = NeonGreen,
    background = DarkGray,
    surface = DarkGray,
    error = NeonRed
)

private val MinimalColorScheme = lightColorScheme(
    primary = Black,
    secondary = Color.Gray,
    background = White,
    surface = White
)

private val RetroColorScheme = darkColorScheme(
    primary = Amber,
    secondary = DarkGreen,
    background = Color(0xFF000000),
    surface = Color(0xFF111111)
)

@Composable
fun NeuroPuzzleTheme(
    puzzleTheme: PuzzleTheme = PuzzleTheme.NEON,
    content: @Composable () -> Unit
) {
    val colorScheme = when (puzzleTheme) {
        PuzzleTheme.NEON -> NeonColorScheme
        PuzzleTheme.MINIMAL -> MinimalColorScheme
        PuzzleTheme.RETRO -> RetroColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
