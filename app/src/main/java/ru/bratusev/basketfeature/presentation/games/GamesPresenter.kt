package ru.bratusev.basketfeature.presentation.games

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.bratusev.basketfeature.presentation.games.adapter.GamesAdapter

class GamesPresenter {

    internal fun initList(recyclerView: RecyclerView) {
        val gamesAdapter =
            GamesAdapter(arrayListOf("Химки", "ЦСКА", "Химки-1", "ЦСКА-1", "Химки-2", "ЦСКА-2"))
        recyclerView.layoutManager =
            LinearLayoutManager(recyclerView.context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = gamesAdapter
    }
}