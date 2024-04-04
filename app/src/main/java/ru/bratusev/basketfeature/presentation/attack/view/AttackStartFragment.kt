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
import ru.bratusev.domain.models.AttackStartType

class AttackStartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start_attack, container, false).also {

            it.findViewById<AppCompatButton>(R.id.startAttack_backBtn).setOnClickListener {
                findNavController().navigate(R.id.action_attackStartFragment_to_timeFragment)
            }
            it.findViewById<AppCompatButton>(R.id.startAttack_btn1)
                .setOnClickListener {
                    GameValues.gameMoment.setAttackStart(AttackStartType.SELECTION_IN_DEFENCE)
                    findNavController().navigate(R.id.action_attackStartFragment_to_timeTypeFragment)
                }
            it.findViewById<AppCompatButton>(R.id.startAttack_btn2).setOnClickListener {
                GameValues.gameMoment.setAttackStart(AttackStartType.INTERCEPTION)
                findNavController().navigate(R.id.action_attackStartFragment_to_timeTypeFragment)
            }
            it.findViewById<AppCompatButton>(R.id.startAttack_btn3).setOnClickListener {
                GameValues.gameMoment.setAttackStart(AttackStartType.LIVE_BALL)
                findNavController().navigate(R.id.action_attackStartFragment_to_timeTypeFragment)
            }
            it.findViewById<AppCompatButton>(R.id.startAttack_btn4).setOnClickListener {
                GameValues.gameMoment.setAttackStart(AttackStartType.DEAD_BALL)
                findNavController().navigate(R.id.action_attackStartFragment_to_timeTypeFragment)
            }
            it.findViewById<AppCompatButton>(R.id.startAttack_btn5).setOnClickListener {
                GameValues.gameMoment.setAttackStart(AttackStartType.SELECTION_IN_ATTACK)
                findNavController().navigate(R.id.action_attackStartFragment_to_timeTypeFragment)
            }
            requireActivity().onBackPressedDispatcher.addCallback(
                viewLifecycleOwner,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        findNavController().navigate(R.id.action_attackStartFragment_to_timeFragment)
                    }
                })
        }
    }
}