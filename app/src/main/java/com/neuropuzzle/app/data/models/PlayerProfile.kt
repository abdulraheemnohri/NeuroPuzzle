package com.neuropuzzle.app.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player_profile")
data class PlayerProfile(
    @PrimaryKey val id: Int = 0,
    var skillLevel: Int = 1,
    var avgSolveTime: Float = 0f,
    var mistakeRate: Float = 0f,
    var strategyType: String = "unknown",
    var adaptabilityScore: Float = 0f,
    // Note: Room might need a TypeConverter for Map, keeping it simple for now or using separate fields
    var patternMemoryJson: String = "{}"
)
