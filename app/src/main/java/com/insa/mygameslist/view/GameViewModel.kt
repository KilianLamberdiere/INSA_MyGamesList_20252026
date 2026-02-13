package com.insa.mygameslist.view

import android.content.Context
import androidx.lifecycle.ViewModel
import com.insa.mygameslist.data.Game
import com.insa.mygameslist.data.IGDB
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {
    private val _games = MutableStateFlow<List<Game>>(emptyList())
    val games: StateFlow<List<Game>> = _games

    fun loadGames() {
        _games.value = IGDB.games
    }

    fun toggleFavorite(gameId: Long) {
        _games.update { list ->
            list.map { game ->
                if (game.id == gameId)
                    game.copy(isFavorite = !game.isFavorite)
                else game
            }
        }
    }
}