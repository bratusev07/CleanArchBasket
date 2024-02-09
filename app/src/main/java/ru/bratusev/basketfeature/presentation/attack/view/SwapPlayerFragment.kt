package ru.bratusev.basketfeature.presentation.attack.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.fragment.app.Fragment
import ru.bratusev.basketfeature.R
import ru.bratusev.basketfeature.presentation.attack.adapter.PlayersGridAdapter

class SwapPlayerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_swap_player, container, false).also {
            it.findViewById<GridView>(R.id.swap_gridView).adapter = PlayersGridAdapter(
                requireContext(),
                arrayListOf(12, 21, 33, 42, 51, 16, 25, 26, 27, 28, 20)
            )
        }
    }
}