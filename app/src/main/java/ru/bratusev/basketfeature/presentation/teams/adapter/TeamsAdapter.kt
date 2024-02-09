package ru.bratusev.basketfeature.presentation.teams.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import ru.bratusev.basketfeature.R
import ru.bratusev.basketfeature.presentation.teams.view.TeamsFragment

class TeamsAdapter(private val items: List<String>, val fragment: TeamsFragment) :
    RecyclerView.Adapter<TeamsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_team, parent, false)
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
        private val teamName: TextView = itemView.findViewById(R.id.teamName)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(item: String) {
            teamName.text = item
        }

        override fun onClick(v: View?) {
            fragment.findNavController().navigate(R.id.action_teamsFragment_to_teamFragment)
        }
    }
}