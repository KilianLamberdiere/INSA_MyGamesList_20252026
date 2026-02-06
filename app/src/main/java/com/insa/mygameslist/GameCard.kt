package com.insa.mygameslist

import android.text.Layout.Alignment.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.insa.mygameslist.data.Genre
import com.insa.mygameslist.data.IGDB

@Composable
fun GameCard(title: String, genres: Set<Genre>, imageUrl: String) {
    Row( modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(
            model = "https:$imageUrl",
            contentDescription = "Game image",
            modifier = Modifier
                .size(80.dp)
                .padding(end = 12.dp),
            contentScale = ContentScale.Crop,
            error = painterResource(R.drawable.cover_placeholder)
        )
        Column {
            Text(title, textDecoration = TextDecoration.Underline)
            Text("Genres : ")
            genres.forEach { genre ->
                Text(text = "• ${genre.name}")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameCardsPreview() {
    LazyColumn {
        items(IGDB.games.size)
        {
            for(game in IGDB.games) {
                GameCard(
                    title = game.name,
                    genres = game.genres,
                    imageUrl = game.cover.url
                )
            }
        }
    }
}
