package ru.bratusev.basketfeature.presentation.games.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel() : ViewModel() {

    private val gameListMutable = MutableLiveData<ArrayList<String>>()
    internal val gameList: LiveData<ArrayList<String>> = gameListMutable

    internal fun getGames() {
        gameListMutable.value = arrayListOf("Игра 1", "Игра 2", "Игра 3", "Игра 4", "Игра 5")
    }

    internal fun updateGame(index: Int, game: String) {}

    internal fun removeGame(game: String) {}
}