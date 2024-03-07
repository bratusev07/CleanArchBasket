package ru.bratusev.basketfeature.presentation.games.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.bratusev.basketfeature.R
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
            it.findViewById<AppCompatButton>(R.id.selectPlayers_nextBtn).setOnClickListener {
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
                playersGrid.adapter = PlayersGridAdapter(requireContext(), vm, vm.players.value!!, true)
            }

            vm.playersInGame.observe(viewLifecycleOwner) {
                playersInGameGrid.adapter = PlayersGridAdapter(requireContext(), vm, vm.playersInGame.value!!, false)
            }

            vm.addPlayers(
                arrayListOf(
                    Player(number = 10),
                    Player(number = 21),
                    Player(number = 32),
                    Player(number = 43),
                    Player(number = 54),
                    Player(number = 65),
                    Player(number = 76),
                    Player(number = 87)
                )
            )

        }
    }
}