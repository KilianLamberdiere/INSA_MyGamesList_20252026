package com.insa.mygameslist.pages

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.insa.mygameslist.components.GameCard
import com.insa.mygameslist.data.IGDB

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameList(onGameClicked: (Long) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = Color.hsv(209f, 0.47f, 1f),
                    titleContentColor = Color.Black,
                ),
                title = { Text("My Games List") })
        },
        contentWindowInsets = WindowInsets.systemBars,
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        LazyColumn (modifier =  Modifier.padding(innerPadding)){
            items(IGDB.games.size)
            { index ->
                val game = IGDB.games[index]
                GameCard(
                    title = game.name,
                    genres = game.genres,
                    imageUrl = game.cover.url,
                    onClick = { onGameClicked(game.id) }
                )
            }
        }
    }
}