package ru.bratusev.basketfeature.presentation.games.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import ru.bratusev.basketfeature.R
import ru.bratusev.basketfeature.presentation.games.view.SelectEnemyViewModel
import ru.bratusev.domain.models.Player

class EnemyGridAdapter(
    private val context: Context,
    private val vm: SelectEnemyViewModel,
    private val players: ArrayList<Player>,
    private val isToGame: Boolean
) : BaseAdapter() {

    private var enemyInGameCount = 0

    override fun getCount(): Int {
        return players.size
    }

    override fun getItem(position: Int): Player {
        return players[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView
        if (convertView == null) {
            val inflater = LayoutInflater.from(context)
            convertView = inflater.inflate(R.layout.grid_item, parent, false)
        }

        convertView?.findViewById<TextView>(R.id.textView)?.text =
            players[position].number.toString()
        convertView?.setOnClickListener {
            val player = players[position]
            try {
                if (isToGame && enemyInGameCount < 5) {
                    vm.addToGame(player)
                    enemyInGameCount++
                } else{
                    vm.removeFromGame(player)
                    enemyInGameCount--
                }
            }catch (e: Exception){
                Toast.makeText(context, "Добавьте ещё игроков в команду: ${5 - (vm.players.value?.size ?: 0)}", Toast.LENGTH_SHORT).show()
            }
        }

        return convertView
    }

}