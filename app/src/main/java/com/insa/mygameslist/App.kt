package com.insa.mygameslist

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.insa.mygameslist.pages.GameList
import com.insa.mygameslist.pages.GamePage
import com.insa.mygameslist.ui.theme.MyGamesListTheme

@Composable
fun App() {
    val navController = rememberNavController()

    MyGamesListTheme {
        NavHost(
            navController,
            startDestination = "list"
        ) {
            composable(
                "list"
            ) {
                GameList(
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
                    GamePage(gameId = gameId)
                }
            }
        }
    }
}