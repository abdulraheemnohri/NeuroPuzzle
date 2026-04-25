package com.neuropuzzle.app.data.models

data class PuzzleDNA(
    var complexity: Int,
    var deceptionLevel: Int,
    var branchingFactor: Int,
    var timePressure: Boolean,
    var rules: MutableList<String>
)
