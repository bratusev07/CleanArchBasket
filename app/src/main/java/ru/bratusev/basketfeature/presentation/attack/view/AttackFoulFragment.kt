package ru.bratusev.basketfeature.presentation.attack.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.bratusev.basketfeature.R
import ru.bratusev.basketfeature.presentation.attack.GameValues
import ru.bratusev.basketfeature.presentation.attack.dialogs.ScoredBallsDialog
import ru.bratusev.basketfeature.presentation.attack.dialogs.TeamFoulDialog
import ru.bratusev.domain.models.Foul

class AttackFoulFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_attack_foul, container, false).also {
            it.findViewById<AppCompatButton>(R.id.attackFoul_backBtn)
                .setOnClickListener { findNavController().navigate(R.id.action_attackFoulFragment_to_attackResultFragment) }
            it.findViewById<AppCompatButton>(R.id.attackFoul_btn1)
                .setOnClickListener {
                    GameValues.gameMoment.setFoul(Foul.SHOT_1)
                    ScoredBallsDialog(1).show(
                        childFragmentManager,
                        "ScoredBall"
                    )
                }
            it.findViewById<AppCompatButton>(R.id.attackFoul_btn2)
                .setOnClickListener {
                    GameValues.gameMoment.setFoul(Foul.SHOT_2)
                    ScoredBallsDialog(2).show(
                        childFragmentManager,
                        "ScoredBall"
                    )
                }
            it.findViewById<AppCompatButton>(R.id.attackFoul_btn3)
                .setOnClickListener {
                    GameValues.gameMoment.setFoul(Foul.SHOT_3)
                    ScoredBallsDialog(3).show(
                        childFragmentManager,
                        "ScoredBall"
                    )
                }
            it.findViewById<AppCompatButton>(R.id.attackFoul_btn4)
                .setOnClickListener {
                    GameValues.gameMoment.setFoul(Foul.NOT_PUNCHY)
                    findNavController().navigate(R.id.action_attackFoulFragment_to_timeFragment)
                }
            it.findViewById<AppCompatButton>(R.id.attackFoul_btn5)
                .setOnClickListener {
                    GameValues.gameMoment.setFoul(Foul.TECHNICAL)
                    TeamFoulDialog().show(childFragmentManager, "TeamFoul")
                }
            requireActivity().onBackPressedDispatcher.addCallback(
                viewLifecycleOwner,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        findNavController().navigate(R.id.action_attackFoulFragment_to_attackResultFragment)
                    }
                })
        }
    }
}