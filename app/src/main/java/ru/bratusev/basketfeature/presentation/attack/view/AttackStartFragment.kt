package ru.bratusev.basketfeature.presentation.attack.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.bratusev.basketfeature.R
import ru.bratusev.basketfeature.presentation.attack.GameValues
import ru.bratusev.domain.models.AttackStartType

class AttackStartFragment : Fragment(), OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start_attack, container, false).also {
            it.findViewById<AppCompatButton>(R.id.startAttack_backBtn).setOnClickListener(this)
            it.findViewById<AppCompatButton>(R.id.startAttack_btn1).setOnClickListener(this)
            it.findViewById<AppCompatButton>(R.id.startAttack_btn2).setOnClickListener(this)
            it.findViewById<AppCompatButton>(R.id.startAttack_btn3).setOnClickListener(this)
            it.findViewById<AppCompatButton>(R.id.startAttack_btn4).setOnClickListener(this)
            it.findViewById<AppCompatButton>(R.id.startAttack_btn5).setOnClickListener(this)
            requireActivity().onBackPressedDispatcher.addCallback(
                viewLifecycleOwner,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        findNavController().navigate(R.id.action_attackStartFragment_to_timeFragment)
                    }
                })
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.startAttack_btn1 -> setData(AttackStartType.SELECTION_IN_DEFENCE)
            R.id.startAttack_btn2 -> setData(AttackStartType.INTERCEPTION)
            R.id.startAttack_btn3 -> setData(AttackStartType.LIVE_BALL)
            R.id.startAttack_btn4 -> setData(AttackStartType.DEAD_BALL)
            R.id.startAttack_btn5 -> setData(AttackStartType.SELECTION_IN_ATTACK)
            R.id.startAttack_backBtn -> findNavController().navigate(R.id.action_attackStartFragment_to_timeFragment)
        }
    }

    private fun setData(attackType: AttackStartType) {
        GameValues.gameMoment.setAttackStart(attackType)
        findNavController().navigate(R.id.action_attackStartFragment_to_timeTypeFragment)
    }
}