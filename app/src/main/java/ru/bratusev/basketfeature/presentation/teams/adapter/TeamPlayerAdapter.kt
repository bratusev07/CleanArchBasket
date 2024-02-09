package ru.bratusev.basketfeature.presentation.teams.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.bratusev.basketfeature.R

class TeamPlayerAdapter(private val items: List<String>) :
    RecyclerView.Adapter<TeamPlayerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_players, parent, false)
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
        private val playerName: TextView = itemView.findViewById(R.id.playerName)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(item: String) {
            playerName.text = item
        }

        override fun onClick(v: View?) {}
    }
}