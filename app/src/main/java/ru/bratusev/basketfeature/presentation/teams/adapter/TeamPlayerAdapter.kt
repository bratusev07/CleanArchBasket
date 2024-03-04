package ru.bratusev.basketfeature.presentation.teams.adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import ru.bratusev.basketfeature.R
import ru.bratusev.basketfeature.presentation.teams.view.TeamFragment

class TeamPlayerAdapter(
    private val items: List<String>,
    val fragment: TeamFragment,
) :
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


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val playerName: TextView = itemView.findViewById(R.id.playerName)

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

                    } else if (deltaX < -50) {

                    } else fragment.findNavController()
                        .navigate(R.id.action_teamsFragment_to_teamFragment)

                    true
                }

                else -> false
            }
        }

        init {
            itemView.setOnTouchListener(touchListener)
        }

        fun bind(item: String) {
            playerName.text = item
        }
    }
}