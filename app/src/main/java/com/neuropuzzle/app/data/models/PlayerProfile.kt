package com.neuropuzzle.app.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player_profile")
data class PlayerProfile(
    @PrimaryKey val id: Int = 0,
    var skillLevel: Int = 1,
    var currentLevel: Int = 1,
    var experiencePoints: Int = 0,
    var avgSolveTime: Float = 0f,
    var mistakeRate: Float = 0f,
    var strategyType: String = "unknown",
    var adaptabilityScore: Float = 0f,
    var patternMemoryJson: String = "{}"
)
