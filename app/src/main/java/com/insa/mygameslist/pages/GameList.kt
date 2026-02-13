package com.insa.mygameslist.pages

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.insa.mygameslist.components.GameCard
import com.insa.mygameslist.data.IGDB

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameList(onGameClicked: (Long) -> Unit) {
    var searchOpen by remember { mutableStateOf(false) }
    var query by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    return Scaffold(
        topBar = {
            if (searchOpen) {
                SearchBar(
                    inputField = {
                        SearchBarDefaults.InputField(
                            placeholder = { Text("Search games...") },
                            query = query,
                            onQueryChange = { query = it },
                            expanded = searchOpen,
                            onExpandedChange = { searchOpen = it },
                            onSearch = { },
                            modifier = Modifier.focusRequester(focusRequester)
                        )
                    },
                    expanded = searchOpen,
                    onExpandedChange = { searchOpen = it },
                ) {}
            } else {
                TopAppBar(
                    colors = topAppBarColors(
                        containerColor = Color.hsv(209f, 0.47f, 1f),
                        titleContentColor = Color.Black,
                    ),
                    title = {
                        Text("My Games List")
                    },
                    actions = {
                        if (!searchOpen) {
                            IconButton(
                                onClick = {
                                    searchOpen = true
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Search,
                                    contentDescription = "Refresh"
                                )
                            }
                        }
                    }
                )
            }
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