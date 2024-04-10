package ru.bratusev.basketfeature.presentation.attack.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.bratusev.basketfeature.R
import ru.bratusev.basketfeature.presentation.attack.GameValues
import ru.bratusev.basketfeature.presentation.attack.adapter.SwapGridAdapter
import ru.bratusev.basketfeature.presentation.games.adapter.PlayersGridAdapter
import ru.bratusev.domain.models.Player

class PlayersSwapFragment: Fragment() {

    private val vm: PlayersSwapViewModel by viewModel<PlayersSwapViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_swap_player, container, false).also {
            it.findViewById<TextView>(R.id.swapPlayer_teamName).text = if(GameValues.isEnemy) GameValues.myTeam.name else GameValues.enemyTeam.name
            val playersGrid = it.findViewById<GridView>(R.id.swapPlayer_playersList)
            val playersInGameGrid = it.findViewById<GridView>(R.id.swapPlayer_playersListInGame)
            val nextBtn = it.findViewById<AppCompatButton>(R.id.swapPlayer_swapButton)
            nextBtn.setOnClickListener {
                findNavController().navigate(R.id.action_playersSwapFragment_to_timeTypeFragment)
            }

            vm.players.observe(viewLifecycleOwner) {
                val toGame = vm.players.value!!.filter { elem -> !elem.isInGame } as ArrayList<Player>
                val fromGame = vm.players.value!!.filter { elem -> elem.isInGame } as ArrayList<Player>
                playersGrid.adapter = SwapGridAdapter(requireContext(), vm, toGame, true)
                playersInGameGrid.adapter = SwapGridAdapter(requireContext(), vm, fromGame, false)
                if(fromGame.size < 5){
                    nextBtn.background = requireContext().getDrawable(R.drawable.button_style_stroke_grey)
                    nextBtn.setTextColor(requireContext().getColor(R.color.dark_grey))
                    nextBtn.isClickable = false
                }else{
                    nextBtn.background = requireContext().getDrawable(R.drawable.button_style_stroke)
                    nextBtn.setTextColor(requireContext().getColor(R.color.orange))
                    nextBtn.isClickable = true
                }
            }
        }
    }
}