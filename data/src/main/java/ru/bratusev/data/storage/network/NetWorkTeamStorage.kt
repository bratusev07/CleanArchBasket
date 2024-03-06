package ru.bratusev.data.storage.network

import android.content.Context
import ru.bratusev.data.storage.TeamStorage
import ru.bratusev.data.storage.TeamsStorage
import ru.bratusev.data.storage.models.PlayerModel
import ru.bratusev.data.storage.models.TeamModel

class NetWorkTeamStorage(context: Context) : TeamStorage {
    override fun getPlayers(): ArrayList<PlayerModel> {
        Common.retrofitService.getPlayers()
        return arrayListOf(
            PlayerModel("Иван", 12, "Федотов"),
            PlayerModel("Николай", 42, "Фролов"),
            PlayerModel("Игорь", 21, "Петров"),
            PlayerModel("Илья", 43, "Плюшкин"),
            PlayerModel("Олег", 13, "Смирнов"),
        )
    }

    override fun removePlayer(index: Int): Boolean {
        Common.retrofitService.removePlayer(index)
        return true
    }

    override fun updatePlayer(player: PlayerModel, index: Int): Boolean {
        Common.retrofitService.updatePlayer(index, player)
        return true
    }

    override fun createPlayer(player: PlayerModel): Boolean {
        Common.retrofitService.createPlayer(player)
        return true
    }

}