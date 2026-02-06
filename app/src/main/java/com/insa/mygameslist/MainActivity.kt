package com.insa.mygameslist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.insa.mygameslist.data.IGDB
import com.insa.mygameslist.ui.theme.MyGamesListTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        IGDB.load(this)

        enableEdgeToEdge()
        setContent {

            MyGamesListTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            colors = topAppBarColors(
                                containerColor = Color.Magenta,
                                titleContentColor = Color.Black,
                            ),
                            title = { Text("My Games List") })
                    },
                    contentWindowInsets = WindowInsets.systemBars,
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    Text("À remplir", modifier = Modifier.padding(innerPadding))
                    LazyColumn (modifier =  Modifier.padding(innerPadding)){
                        items(IGDB.games.size)
                        { index ->
                            val game = IGDB.games[index]
                            GameCard(
                                title = game.name,
                                genres = game.genres,
                                imageUrl = game.cover.url
                            )
                        }
                    }
                }
            }
        }
    }
}