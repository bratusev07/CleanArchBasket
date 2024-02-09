package ru.bratusev.basketfeature.presentation.games.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.bratusev.basketfeature.R

class GamesAdapter(private val items: List<String>) :
    RecyclerView.Adapter<GamesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_game, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), OnClickListener {
        private val gameName: TextView = itemView.findViewById(R.id.gameName)
        private val gameDate: TextView = itemView.findViewById(R.id.gameDate)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(item: String) {
            gameName.text = item
        }

        override fun onClick(v: View?) {}
    }
}