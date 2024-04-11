package ru.bratusev.basketfeature.presentation.games.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.bratusev.basketfeature.R
import ru.bratusev.basketfeature.presentation.attack.GameValues
import ru.bratusev.basketfeature.presentation.attack.GameValues.myTeam
import ru.bratusev.basketfeature.presentation.games.adapter.PlayersGridAdapter
import ru.bratusev.domain.models.Player

class SelectPlayersFragment : Fragment() {

    private val vm: SelectPlayersViewModel by viewModel<SelectPlayersViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_select_players, container, false).also {
            val playersGrid = it.findViewById<GridView>(R.id.selectPlayer_gridView)
            val playersInGameGrid = it.findViewById<GridView>(R.id.selectedPlayer_gridView)
            val progressBar = it.findViewById<ProgressBar>(R.id.selectPlayers_progressBar)
            vm.setTeamId(myTeam.id)
            it.findViewById<TextView>(R.id.selectPlayers_teamName).text = myTeam.name
            val nextBtn = it.findViewById<AppCompatButton>(R.id.selectPlayers_nextBtn)
            nextBtn.setOnClickListener {
                GameValues.myPlayers = vm.players.value as ArrayList<Player>
                findNavController().navigate(R.id.action_selectPlayersFragment_to_selectEnemyFragment)
            }
            it.findViewById<ImageView>(R.id.selectPlayers_back).setOnClickListener {
                findNavController().navigate(R.id.action_selectPlayersFragment_to_selectTeamsFragment)
            }
            requireActivity().onBackPressedDispatcher.addCallback(
                viewLifecycleOwner,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        findNavController().navigate(R.id.action_selectPlayersFragment_to_selectTeamsFragment)
                    }
                })

            vm.players.observe(viewLifecycleOwner) {
                val toGame = vm.players.value!!.filter { elem -> !elem.isInGame } as ArrayList<Player>
                val fromGame = vm.players.value!!.filter { elem -> elem.isInGame } as ArrayList<Player>
                playersGrid.adapter = PlayersGridAdapter(requireContext(), vm, toGame, true)
                playersInGameGrid.adapter = PlayersGridAdapter(requireContext(), vm, fromGame, false)
                if(fromGame.size < 5){
                    nextBtn.background = requireContext().getDrawable(R.drawable.button_style_stroke_grey)
                    nextBtn.isClickable = false
                }else{
                    nextBtn.background = requireContext().getDrawable(R.drawable.button_style_stroke)
                    nextBtn.isClickable = true
                    nextBtn.setTextColor(requireContext().getColor(R.color.orange))
                }
            }

            vm.isLoading.observe(viewLifecycleOwner){
                if (vm.isLoading.value == true) {
                    progressBar.visibility = View.VISIBLE
                } else {
                    progressBar.visibility = View.GONE
                }
            }
            vm.getPlayers()
        }
    }
}