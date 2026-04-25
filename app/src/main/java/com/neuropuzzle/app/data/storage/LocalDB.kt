package com.neuropuzzle.app.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.neuropuzzle.app.data.models.GameStats
import com.neuropuzzle.app.data.models.PlayerProfile

@Database(entities = [PlayerProfile::class, GameStats::class], version = 1, exportSchema = false)
abstract class LocalDB : RoomDatabase() {
    abstract fun puzzleDao(): PuzzleDao
}
