package com.neuropuzzle.app.data.storage

import androidx.room.*
import com.neuropuzzle.app.data.models.GameStats
import com.neuropuzzle.app.data.models.PlayerProfile

@Dao
interface PuzzleDao {
    @Query("SELECT * FROM player_profile WHERE id = 0")
    suspend fun getPlayerProfile(): PlayerProfile?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePlayerProfile(profile: PlayerProfile)

    @Insert
    suspend fun insertGameStats(stats: GameStats)

    @Query("SELECT * FROM game_stats ORDER BY date DESC")
    suspend fun getAllGameStats(): List<GameStats>
}
