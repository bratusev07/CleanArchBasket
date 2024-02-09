package ru.bratusev.basketfeature.presentation.teams

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.bratusev.basketfeature.presentation.teams.adapter.TeamPlayerAdapter

class TeamPresenter {

    internal fun initList(recyclerView: RecyclerView) {
        val teamPlayerAdapter =
            TeamPlayerAdapter(arrayListOf("Вова", "Валера", "Андрей", "Коля", "Петя", "Вася"))
        recyclerView.layoutManager =
            LinearLayoutManager(recyclerView.context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = teamPlayerAdapter
    }

}