package com.insa.mygameslist.pages

import android.util.Log
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.insa.mygameslist.R
import com.insa.mygameslist.data.IGDB

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GamePage(gameId: Long) {
    val game = IGDB.games.firstOrNull { it.id == gameId }
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = Color.hsv(100f, 0.47f, 1f),
                    titleContentColor = Color.Black,
                ),
                title = { Text(game?.name ?: "Game not found") })
        },
        contentWindowInsets = WindowInsets.systemBars,
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        if (game != null) {
            Column(
                modifier = Modifier.padding(innerPadding).fillMaxWidth()
            ) {
                Text(
                    text = game.name,
                    textDecoration = TextDecoration.Underline,
                    textAlign = TextAlign.Center
                )
                AsyncImage(
                    model = "https:${game.cover.url}",
                    contentDescription = "Game image",
                    modifier = Modifier
                        .size(80.dp)
                        .padding(end = 12.dp),
                    contentScale = ContentScale.Crop,
                    error = painterResource(R.drawable.broken_image)
                )
                Text(
                    game.genres.joinToString(", ") { it.name },
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )

                Row(modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.Center){
                    for(p in game.platforms){

                        var imageUrl = "https:${p.logo?.url}"
                        if(imageUrl.endsWith("jpg")){
                            imageUrl = imageUrl.replace("jpg","png")
                        }

                        Log.d("PNG", imageUrl)

                        AsyncImage(
                            model = imageUrl,
                            contentDescription = "Platform image",
                            modifier = Modifier
                                .size(40.dp)
                                .padding(end = 8.dp),
                            contentScale = ContentScale.Fit,
                            error = painterResource(R.drawable.broken_image)
                        )
                    }
                }
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = game.summary,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        } else {
            Text("Game not found")
        }
    }
}