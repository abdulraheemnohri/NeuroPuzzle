package com.neuropuzzle.app.data.storage

import android.content.Context

class Preferences(context: Context) {
    private val prefs = context.getSharedPreferences("neuro_puzzle_prefs", Context.MODE_PRIVATE)

    fun isFirstRun(): Boolean = prefs.getBoolean("first_run", true)
    fun setFirstRun(value: Boolean) = prefs.edit().putBoolean("first_run", value).apply()

    fun getLastComplexity(): Int = prefs.getInt("last_complexity", 1)
    fun setLastComplexity(value: Int) = prefs.edit().putInt("last_complexity", value).apply()
}
