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
import ru.bratusev.domain.models.Loss

class AttackLostFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_attack_lost, container, false).also {
            it.findViewById<AppCompatButton>(R.id.attackLost_backBtn)
                .setOnClickListener { findNavController().navigate(R.id.action_attackLostFragment_to_attackResultFragment) }
            it.findViewById<AppCompatButton>(R.id.attackLost_btn1)
                .setOnClickListener {
                    GameValues.gameMoment.setLoss(Loss.PASS_LOSS)
                    findNavController().navigate(R.id.action_attackLostFragment_to_timeFragment)
                }
            it.findViewById<AppCompatButton>(R.id.attackLost_btn2)
                .setOnClickListener {
                    GameValues.gameMoment.setLoss(Loss.TECHNICAL_LOSS)
                    findNavController().navigate(R.id.action_attackLostFragment_to_timeFragment)
                }
            it.findViewById<AppCompatButton>(R.id.attackLost_btn3)
                .setOnClickListener {
                    GameValues.gameMoment.setLoss(Loss.FOUL_IN_ATTACK)
                    findNavController().navigate(R.id.action_attackLostFragment_to_timeFragment)
                }
            it.findViewById<AppCompatButton>(R.id.attackLost_btn4)
                .setOnClickListener {
                    GameValues.gameMoment.setLoss(Loss.TACTICAL_LOSS)
                    findNavController().navigate(R.id.action_attackLostFragment_to_timeFragment)
                }
            requireActivity().onBackPressedDispatcher.addCallback(
                viewLifecycleOwner,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        findNavController().navigate(R.id.action_attackLostFragment_to_attackResultFragment)
                    }
                })
        }
    }
}