package com.insa.mygameslist.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.insa.mygameslist.data.Game
import com.insa.mygameslist.data.GameRepository
//import com.insa.mygameslist.data.IGDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {
    private val repository = GameRepository()
    private val _games = MutableStateFlow<List<Game>>(emptyList())
    val games: StateFlow<List<Game>> = _games

    init {
        loadGames()
    }
    fun loadGames() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _games.value = repository.getGames()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
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