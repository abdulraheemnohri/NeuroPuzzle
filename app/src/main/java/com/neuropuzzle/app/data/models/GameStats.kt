package com.neuropuzzle.app.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_stats")
data class GameStats(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val puzzleId: String,
    val solveTime: Long,
    val mistakes: Int,
    val date: Long = System.currentTimeMillis()
)
