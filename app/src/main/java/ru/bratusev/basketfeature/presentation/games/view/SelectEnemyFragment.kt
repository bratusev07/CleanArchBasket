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
import ru.bratusev.basketfeature.presentation.attack.GameValues.date
import ru.bratusev.basketfeature.presentation.attack.GameValues.enemyPlayers
import ru.bratusev.basketfeature.presentation.attack.GameValues.enemyTeam
import ru.bratusev.basketfeature.presentation.attack.GameValues.myTeam
import ru.bratusev.basketfeature.presentation.games.adapter.EnemyGridAdapter
import ru.bratusev.domain.models.GameModel
import ru.bratusev.domain.models.Player

class SelectEnemyFragment : Fragment() {

    private val vm: SelectEnemyViewModel by viewModel<SelectEnemyViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_select_enemy, container, false).also {
            vm.setTeamId(enemyTeam.id)
            val playersGrid = it.findViewById<GridView>(R.id.selectEnemy_GridView)
            val progressBar = it.findViewById<ProgressBar>(R.id.selectEnemy_progressBar)
            val playersInGameGrid = it.findViewById<GridView>(R.id.selectedEnemy_GridView)

            it.findViewById<TextView>(R.id.selectEnemy_teamName).text = enemyTeam.name
            it.findViewById<ImageView>(R.id.selectEnemy_back).setOnClickListener {
                findNavController().navigate(R.id.action_selectEnemyFragment_to_selectPlayersFragment)
            }
            val nextBtn = it.findViewById<AppCompatButton>(R.id.selectEnemy_startGameBtn)
            nextBtn.setOnClickListener {
                enemyPlayers = vm.players.value as ArrayList<Player>
                vm.createGame(GameModel("",date, myTeam.id, enemyTeam.id, "${myTeam.name} – ${enemyTeam.name}"))
                findNavController().navigate(R.id.action_selectEnemyFragment_to_timeFragment)
            }
            requireActivity().onBackPressedDispatcher.addCallback(
                viewLifecycleOwner,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        findNavController().navigate(R.id.action_selectEnemyFragment_to_selectPlayersFragment)
                    }
                })

            vm.players.observe(viewLifecycleOwner) {
                val toGame = vm.players.value!!.filter { elem -> !elem.isInGame } as ArrayList<Player>
                val fromGame = vm.players.value!!.filter { elem -> elem.isInGame } as ArrayList<Player>
                playersGrid.adapter = EnemyGridAdapter(requireContext(), vm, toGame, true)
                playersInGameGrid.adapter = EnemyGridAdapter(requireContext(), vm, fromGame, false)
                if(fromGame.size < 5){
                    nextBtn.background = requireContext().getDrawable(R.drawable.button_style_stroke_grey)
                    nextBtn.setTextColor(requireContext().getColor(R.color.dark_grey))
                    nextBtn.isClickable = false
                }else{
                    nextBtn.background = requireContext().getDrawable(R.drawable.button_style)
                    nextBtn.setTextColor(requireContext().getColor(R.color.black))
                    nextBtn.isClickable = true
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