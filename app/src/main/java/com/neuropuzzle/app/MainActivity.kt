package com.neuropuzzle.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.neuropuzzle.app.data.storage.LocalDB
import com.neuropuzzle.app.engine.analyzer.PlayerAnalyzer
import com.neuropuzzle.app.engine.core.GameManager
import com.neuropuzzle.app.engine.generator.PuzzleFactory
import com.neuropuzzle.app.engine.generator.PuzzleGenerator
import com.neuropuzzle.app.engine.mutation.MutationEngine
import com.neuropuzzle.app.ui.screens.AnalysisScreen
import com.neuropuzzle.app.ui.screens.GameScreen
import com.neuropuzzle.app.ui.screens.HomeScreen
import com.neuropuzzle.app.ui.screens.ResultScreen
import com.neuropuzzle.app.ui.theme.NeuroPuzzleTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var db: LocalDB
    private lateinit var gameManager: GameManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = Room.databaseBuilder(
            applicationContext,
            LocalDB::class.java, "neuropuzzle-db"
        ).build()

        gameManager = GameManager(
            generator = PuzzleGenerator(),
            analyzer = PlayerAnalyzer(),
            mutationEngine = MutationEngine(),
            dao = db.puzzleDao()
        )

        lifecycleScope.launch {
            gameManager.loadProfile()
        }

        setContent {
            var currentScreen by remember { mutableStateOf("home") }
            var currentPuzzle by remember { mutableStateOf(gameManager.startNewGame(PuzzleFactory.createDefaultDNA())) }
            val playerProfile by gameManager.playerProfile.collectAsState()

            NeuroPuzzleTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    when (currentScreen) {
                        "home" -> HomeScreen(
                            onStartGame = { currentScreen = "game" },
                            onViewAnalysis = { currentScreen = "analysis" }
                        )
                        "game" -> GameScreen(
                            puzzle = currentPuzzle,
                            onGameFinished = { path ->
                                lifecycleScope.launch {
                                    gameManager.onGameFinished(
                                        puzzleId = currentPuzzle.id,
                                        moves = path.map { "${it.first},${it.second}" },
                                        solveTime = 45000,
                                        mistakes = 2
                                    )
                                    currentScreen = "result"
                                }
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
                            AnalysisScreen(
                                strategy = playerProfile.strategyType,
                                avgTime = playerProfile.avgSolveTime / 1000f,
                                mistakeRate = playerProfile.mistakeRate
                            )
                        }
                    }
                }
            }
        }
    }
}
