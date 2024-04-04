package ru.bratusev.basketfeature.presentation.attack.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.bratusev.basketfeature.R
import ru.bratusev.basketfeature.presentation.attack.GameValues
import ru.bratusev.basketfeature.presentation.attack.GameValues.gameMoment
import ru.bratusev.basketfeature.presentation.attack.GameValues.isEnemy
import ru.bratusev.basketfeature.presentation.attack.adapter.TimesGridAdapter
import ru.bratusev.basketfeature.presentation.attack.dialogs.FinishGameDialog

class TimeFragment : Fragment() {

    private val vm: TimeViewModel by viewModel<TimeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_time, container, false).also {
            val grid = (it.findViewById<GridView>(R.id.custom_view))
            val adapter = TimesGridAdapter(requireContext(), arrayListOf(1, 2, 3, 4, 0))
            grid.adapter = adapter

            val myTeam = it.findViewById<AppCompatButton>(R.id.time_myTeam)
            myTeam.setOnClickListener {
                isEnemy = false
                gameMoment.setTeam(GameValues.myTeam.name).setTimeZone(adapter.getTimeZone())
                findNavController().navigate(R.id.action_timeFragment_to_attackStartFragment)
            }
            val enemyTeam = it.findViewById<AppCompatButton>(R.id.time_enemyTeam)
            enemyTeam.setOnClickListener {
                isEnemy = true
                gameMoment.setTeam(GameValues.enemyTeam.name).setTimeZone(adapter.getTimeZone())
                findNavController().navigate(R.id.action_timeFragment_to_attackStartFragment)
            }
            myTeam.text = GameValues.myTeam.name
            enemyTeam.text = GameValues.enemyTeam.name

            it.findViewById<AppCompatButton>(R.id.time_finish).setOnClickListener {
                FinishGameDialog(requireContext(), requireParentFragment()).show()
            }
            requireActivity().onBackPressedDispatcher.addCallback(
                viewLifecycleOwner,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        FinishGameDialog(requireContext(), requireParentFragment()).show()
                    }
                })
        }
    }
}