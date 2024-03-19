package ru.bratusev.basketfeature.presentation.attack.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.fragment.app.Fragment
import ru.bratusev.basketfeature.R
import ru.bratusev.basketfeature.presentation.attack.adapter.PlayersGridAdapter
import ru.bratusev.domain.models.Player

class SwapPlayerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_swap_player, container, false).also {
            it.findViewById<GridView>(R.id.swap_gridView).adapter = PlayersGridAdapter(
                requireContext(),
                arrayListOf(
                    Player(number = 12),
                    Player(number = 21),
                    Player(number = 34),
                    Player(number = 45),
                    Player(number = 62),
                    Player(number = 34),
                    Player(number = 61),
                    Player(number = 66),
                    Player(number = 67),
                    Player(number = 17),
                    Player(number = 47),
                )
            )
        }
    }
}