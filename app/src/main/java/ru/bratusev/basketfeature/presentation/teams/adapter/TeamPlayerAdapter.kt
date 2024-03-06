package ru.bratusev.basketfeature.presentation.teams.adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ru.bratusev.basketfeature.R
import ru.bratusev.basketfeature.presentation.teams.dialogs.UpdatePlayerDialog
import ru.bratusev.basketfeature.presentation.teams.view.TeamFragment
import ru.bratusev.basketfeature.presentation.teams.view.TeamViewModel
import ru.bratusev.domain.models.Player

class TeamPlayerAdapter(
    private val items: ArrayList<Player>,
    private val fragment: TeamFragment,
    private val vm: TeamViewModel
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
        private val playerNumber: TextView = itemView.findViewById(R.id.playerNumber)

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
                        UpdatePlayerDialog(
                            vm,
                            items[position],
                            position
                        ).show(fragment.parentFragmentManager, "UpdatePlayerDialog")
                        Toast.makeText(fragment.requireContext(), "Updated", Toast.LENGTH_SHORT).show()
                    } else if (deltaX < -50) {
                        vm.removePlayer(position)
                        Toast.makeText(fragment.requireContext(), "Deleted", Toast.LENGTH_SHORT).show()
                    }
                    true
                }

                else -> false
            }
        }

        init {
            itemView.setOnTouchListener(touchListener)
        }

        fun bind(item: Player) {
            playerName.text = item.name
            playerNumber.text = item.number.toString()
        }
    }
}