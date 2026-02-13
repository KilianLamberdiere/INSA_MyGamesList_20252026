package com.insa.mygameslist.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.insa.mygameslist.R
import com.insa.mygameslist.data.Genre

@Composable
fun GameCard(title: String, genres: Set<Genre>, imageUrl: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = onClick
    ){
        Row( modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = "https:$imageUrl",
                contentDescription = "Game image",
                modifier = Modifier
                    .size(80.dp)
                    .padding(end = 12.dp),
                contentScale = ContentScale.Fit,
                error = painterResource(R.drawable.cover_placeholder)
            )
            Column {
                Text(
                    text = title,
                    textDecoration = TextDecoration.Underline,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Genres : ${genres.joinToString(", ") { it.name }}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun GameCardsPreview() {
//    GameCard(
//        title = "TEST",
//        genres = listOf("Genre1", "Genre2aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "Genre3", "Genre4","Genre5","Genre6"),
//        imageUrl = "TEST"
//    )
//}
