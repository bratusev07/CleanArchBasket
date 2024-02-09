package ru.bratusev.basketfeature.presentation.teams

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.bratusev.basketfeature.presentation.teams.adapter.TeamsAdapter
import ru.bratusev.basketfeature.presentation.teams.view.TeamsFragment

class TeamsPresenter {

    internal fun initList(recyclerView: RecyclerView, fragment: TeamsFragment) {
        val teamsAdapter = TeamsAdapter(
            arrayListOf("Химки", "ЦСКА", "Химки-1", "ЦСКА-1", "Химки-2", "ЦСКА-2"),
            fragment
        )
        recyclerView.layoutManager =
            LinearLayoutManager(recyclerView.context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = teamsAdapter
    }

}