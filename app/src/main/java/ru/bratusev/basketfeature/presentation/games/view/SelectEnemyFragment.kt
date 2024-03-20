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
import ru.bratusev.basketfeature.presentation.games.adapter.EnemyGridAdapter
import ru.bratusev.domain.models.Player

class SelectEnemyFragment : Fragment() {

    private val vm: SelectEnemyViewModel by viewModel<SelectEnemyViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_select_enemy, container, false).also {
            val playersGrid = it.findViewById<GridView>(R.id.selectEnemy_GridView)
            val playersInGameGrid = it.findViewById<GridView>(R.id.selectedEnemy_GridView)
            it.findViewById<ImageView>(R.id.selectEnemy_back).setOnClickListener {
                findNavController().navigate(R.id.action_selectEnemyFragment_to_selectPlayersFragment)
            }
            it.findViewById<AppCompatButton>(R.id.selectEnemy_startGameBtn).setOnClickListener {
                val bundle = Bundle()
                bundle.putSerializable("GameDate", (arguments?.getSerializable("GameDate")))
                bundle.putSerializable("GameMyTeam", (arguments?.getSerializable("GameMyTeam")))
                bundle.putSerializable("GameEnemyTeam", (arguments?.getSerializable("GameEnemyTeam")))
                findNavController().navigate(R.id.action_selectEnemyFragment_to_timeFragment, bundle)
            }
            requireActivity().onBackPressedDispatcher.addCallback(
                viewLifecycleOwner,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        findNavController().navigate(R.id.action_selectEnemyFragment_to_selectPlayersFragment)
                    }
                })

            vm.players.observe(viewLifecycleOwner) {
                playersGrid.adapter = EnemyGridAdapter(
                    requireContext(),
                    vm,
                    vm.players.value!!,
                    true
                )
            }

            vm.playersInGame.observe(viewLifecycleOwner) {
                playersInGameGrid.adapter =
                    EnemyGridAdapter(
                        requireContext(),
                        vm,
                        vm.playersInGame.value!!,
                        false
                    )
            }


            vm.addPlayers(
                arrayListOf(
                    Player(number = 40),
                    Player(number = 51),
                    Player(number = 62),
                    Player(number = 73),
                    Player(number = 84),
                    Player(number = 95),
                    Player(number = 86),
                    Player(number = 77)
                )
            )

        }
    }
}