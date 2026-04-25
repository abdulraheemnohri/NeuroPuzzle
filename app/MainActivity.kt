package com.neuropuzzle.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.neuropuzzle.app.engine.analyzer.PlayerAnalyzer
import com.neuropuzzle.app.engine.core.GameManager
import com.neuropuzzle.app.engine.generator.PuzzleFactory
import com.neuropuzzle.app.engine.generator.PuzzleGenerator
import com.neuropuzzle.app.engine.mutation.MutationEngine
import com.neuropuzzle.app.ui.screens.AnalysisScreen
import com.neuropuzzle.app.ui.screens.GameScreen
import com.neuropuzzle.app.ui.screens.HomeScreen
import com.neuropuzzle.app.ui.screens.ResultScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gameManager = GameManager(
            generator = PuzzleGenerator(),
            analyzer = PlayerAnalyzer(),
            mutationEngine = MutationEngine()
        )

        setContent {
            var currentScreen by remember { mutableStateOf("home") }
            var currentPuzzle by remember { mutableStateOf(gameManager.startNewGame(PuzzleFactory.createDefaultDNA())) }

            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    when (currentScreen) {
                        "home" -> HomeScreen(
                            onStartGame = { currentScreen = "game" },
                            onViewAnalysis = { currentScreen = "analysis" }
                        )
                        "game" -> GameScreen(
                            puzzle = currentPuzzle,
                            onGameFinished = { path ->
                                gameManager.onGameFinished(path.map { "${it.first},${it.second}" }, 45000, 2)
                                currentScreen = "result"
                            }
                        )
                        "result" -> ResultScreen(
                            mistakes = 2,
                            time = "00:45",
                            onNext = {
                                currentPuzzle = gameManager.startNewGame(gameManager.getCurrentDNA() ?: PuzzleFactory.createDefaultDNA())
                                currentScreen = "game"
                            }
                        )
                        "analysis" -> {
                            val profile = gameManager.getPlayerProfile()
                            AnalysisScreen(
                                strategy = profile.strategyType,
                                avgTime = profile.avgSolveTime / 1000f,
                                mistakeRate = profile.mistakeRate
                            )
                        }
                    }
                }
            }
        }
    }
}
