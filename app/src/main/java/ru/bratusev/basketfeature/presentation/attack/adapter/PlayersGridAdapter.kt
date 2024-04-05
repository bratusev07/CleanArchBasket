package ru.bratusev.basketfeature.presentation.attack.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import ru.bratusev.basketfeature.R
import ru.bratusev.basketfeature.presentation.attack.GameValues
import ru.bratusev.basketfeature.presentation.attack.view.PlayersSwapFragment
import ru.bratusev.basketfeature.presentation.attack.view.TimeTypeFragment
import ru.bratusev.domain.models.Player

class PlayersGridAdapter(private val context: Context, private val fragment: TimeTypeFragment, private val players: ArrayList<Player>) :
    BaseAdapter(), OnLongClickListener {
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

        GameValues.gameMoment.setPlayersOnField(players)
        convertView?.findViewById<TextView>(R.id.textView)?.text = players[position].number.toString()
        convertView?.setOnClickListener {
            GameValues.gameMoment.addPassToStory(players[position])
        }
        convertView?.setOnLongClickListener(this)
        return convertView
    }

    override fun onLongClick(v: View?): Boolean {
        fragment.findNavController().navigate(R.id.action_timeTypeFragment_to_playersSwapFragment)
        return false
    }
}