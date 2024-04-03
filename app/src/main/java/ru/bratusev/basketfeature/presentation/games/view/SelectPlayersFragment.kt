package ru.bratusev.basketfeature.presentation.games.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.bratusev.basketfeature.R
import ru.bratusev.basketfeature.presentation.games.adapter.PlayersGridAdapter
import ru.bratusev.domain.models.Player
import ru.bratusev.domain.models.TeamListResponse

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
            val myTeam = (arguments?.getSerializable("GameMyTeam")) as TeamListResponse
            val enemyTeam = (arguments?.getSerializable("GameEnemyTeam")) as TeamListResponse
            vm.setTeamId(myTeam.id)
            it.findViewById<TextView>(R.id.selectPlayers_teamName).text = myTeam.name
            it.findViewById<AppCompatButton>(R.id.selectPlayers_nextBtn).setOnClickListener {
                val bundle = Bundle()
                bundle.putString("GameDate", (arguments?.getString("GameDate")).toString())
                bundle.putSerializable("GameMyTeam", myTeam)
                bundle.putSerializable("GameEnemyTeam", enemyTeam)
                findNavController().navigate(R.id.action_selectPlayersFragment_to_selectEnemyFragment, bundle)
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

            vm.getPlayers()

        }
    }
}