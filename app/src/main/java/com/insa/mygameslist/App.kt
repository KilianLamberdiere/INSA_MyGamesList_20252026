package com.insa.mygameslist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.insa.mygameslist.pages.GameList
import com.insa.mygameslist.pages.GamePage
import com.insa.mygameslist.ui.theme.MyGamesListTheme
import com.insa.mygameslist.view.GameViewModel

@Composable
fun App() {
    val navController = rememberNavController()
    val viewModel : GameViewModel = viewModel()

    LaunchedEffect(Unit) {
        viewModel.loadGames()
    }

    MyGamesListTheme {
        NavHost(
            navController,
            startDestination = "list"
        ) {
            composable(
                "list"
            ) {
                GameList(
                    viewModel = viewModel,
                    onGameClicked = { gameId ->
                        navController.navigate("game/$gameId")
                    }
                )
            }
            composable(
                "game/{gameId}"
            ) { backStackEntry ->
                val gameId = backStackEntry.arguments?.getString("gameId")?.toLongOrNull()
                if (gameId != null) {
                    GamePage(gameId = gameId,viewModel = viewModel,)
                }
            }
        }
    }
}