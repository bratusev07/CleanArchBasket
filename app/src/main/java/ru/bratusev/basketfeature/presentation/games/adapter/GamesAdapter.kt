package ru.bratusev.basketfeature.presentation.games.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import ru.bratusev.basketfeature.R
import ru.bratusev.basketfeature.presentation.attack.GameValues
import ru.bratusev.basketfeature.presentation.games.view.GameViewModel
import ru.bratusev.domain.models.GameModel

class GamesAdapter(private val items: ArrayList<GameModel>, private val fragment: Fragment, private val vm: GameViewModel) :
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

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val gameName: TextView = itemView.findViewById(R.id.gameName)
        private val gameDate: TextView = itemView.findViewById(R.id.gameDate)

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
                        vm.removeGame(items[position].id)
                    } else {
                        GameValues.gameId = items[position].id
                        fragment.findNavController().navigate(R.id.action_gamesFragment_to_statsFragment)
                    }
                    true
                }
                else -> false
            }
        }


        init {
            itemView.setOnTouchListener(touchListener)
        }

        fun bind(item: GameModel) {
            gameName.text = item.gameName
            gameDate.text = item.date
        }
    }
}