package com.neuropuzzle.app.data.storage

import android.content.Context
import com.neuropuzzle.app.ui.theme.PuzzleTheme

class Preferences(context: Context) {
    private val prefs = context.getSharedPreferences("neuro_puzzle_prefs", Context.MODE_PRIVATE)

    fun isFirstRun(): Boolean = prefs.getBoolean("first_run", true)
    fun setFirstRun(value: Boolean) = prefs.edit().putBoolean("first_run", value).apply()

    fun getTheme(): PuzzleTheme {
        val themeName = prefs.getString("selected_theme", PuzzleTheme.NEON.name)
        return try {
            PuzzleTheme.valueOf(themeName ?: PuzzleTheme.NEON.name)
        } catch (e: Exception) {
            PuzzleTheme.NEON
        }
    }

    fun setTheme(theme: PuzzleTheme) {
        prefs.edit().putString("selected_theme", theme.name).apply()
    }
}
