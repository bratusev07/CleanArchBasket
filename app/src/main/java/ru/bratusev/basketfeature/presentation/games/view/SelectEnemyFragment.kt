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
import ru.bratusev.basketfeature.R
import ru.bratusev.basketfeature.presentation.attack.adapter.PlayersGridAdapter

class SelectEnemyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_select_enemy, container, false).also {
            it.findViewById<GridView>(R.id.selectEnemy_GridView).adapter = PlayersGridAdapter(
                requireContext(),
                arrayListOf(12, 21, 33, 42, 51, 16, 25, 26, 27, 28, 20)
            )
            it.findViewById<GridView>(R.id.selectedEnemy_GridView).adapter = PlayersGridAdapter(
                requireContext(),
                arrayListOf(10, 11, 12, 13, 14)
            )
            it.findViewById<ImageView>(R.id.selectEnemy_back).setOnClickListener {
                findNavController().navigate(R.id.action_selectEnemyFragment_to_selectPlayersFragment)
            }
            it.findViewById<AppCompatButton>(R.id.selectEnemy_startGameBtn).setOnClickListener {
                findNavController().navigate(R.id.action_selectEnemyFragment_to_timeFragment)
            }
            requireActivity().onBackPressedDispatcher.addCallback(
                viewLifecycleOwner,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        findNavController().navigate(R.id.action_selectEnemyFragment_to_selectPlayersFragment)
                    }
                })
        }
    }
}