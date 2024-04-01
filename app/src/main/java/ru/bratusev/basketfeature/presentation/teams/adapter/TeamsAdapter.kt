package ru.bratusev.basketfeature.presentation.teams.adapter

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import ru.bratusev.basketfeature.R
import ru.bratusev.basketfeature.presentation.teams.dialogs.UpdateTeamDialog
import ru.bratusev.basketfeature.presentation.teams.view.TeamsFragment
import ru.bratusev.basketfeature.presentation.teams.view.TeamsViewModel
import ru.bratusev.domain.models.TeamListResponse

class TeamsAdapter(
    private val items: ArrayList<TeamListResponse>?,
    val fragment: TeamsFragment,
    private val vm: TeamsViewModel
) :
    RecyclerView.Adapter<TeamsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_team, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items?.get(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val teamName: TextView = itemView.findViewById(R.id.teamName)

        @SuppressLint("ClickableViewAccessibility")
        private val touchListener = View.OnTouchListener { view, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    view.tag = motionEvent.x
                    true
                }

                MotionEvent.ACTION_UP -> {
                    val startX = view.tag as Float
                    val endX = motionEvent.x
                    val deltaX = endX - startX
                    if (deltaX > 50) {
                        vm.removeTeam(items?.get(position)?.id ?: "")
                    } else if (deltaX < -50) {
                        UpdateTeamDialog(fragment.requireContext()).show(items?.get(position)?.id ?: "", vm)
                    } else {
                        val bundle = Bundle()
                        bundle.putString("teamId", items?.get(position)?.id)
                        fragment.findNavController()
                            .navigate(R.id.action_teamsFragment_to_teamFragment, bundle)
                    }
                    true
                }
                else -> false
            }
        }

        init {
            itemView.setOnTouchListener(touchListener)
        }

        fun bind(item: TeamListResponse) {
            teamName.text = item.name
        }

    }
}