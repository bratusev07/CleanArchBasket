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
import ru.bratusev.domain.models.AttackType

class AttackTypeFragment : Fragment(), OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_attack_type, container, false).also {
            it.findViewById<AppCompatButton>(R.id.attackType_backBtn).setOnClickListener(this)
            it.findViewById<AppCompatButton>(R.id.attackType_btn1).setOnClickListener(this)
            it.findViewById<AppCompatButton>(R.id.attackType_btn2).setOnClickListener(this)
            it.findViewById<AppCompatButton>(R.id.attackType_btn3).setOnClickListener(this)
            it.findViewById<AppCompatButton>(R.id.attackType_btn4).setOnClickListener(this)
            it.findViewById<AppCompatButton>(R.id.attackType_btn5).setOnClickListener(this)
            it.findViewById<AppCompatButton>(R.id.attackType_btn6).setOnClickListener(this)
            requireActivity().onBackPressedDispatcher.addCallback(
                viewLifecycleOwner,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        findNavController().navigate(R.id.action_attackTypeFragment_to_timeTypeFragment)
                    }
                })
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.attackType_btn1 -> setData(AttackType.QUICK_BREAKAWAY)
            R.id.attackType_btn2 -> setData(AttackType.EARLY_ATTACK)
            R.id.attackType_btn3 -> setData(AttackType.SECOND_CHANCE_ATTACK)
            R.id.attackType_btn4 -> setData(AttackType.POSITIONAL_ATTACK)
            R.id.attackType_btn5 -> setData(AttackType.BREAKING_PRESSURE)
            R.id.attackType_btn6 -> setData(AttackType.BREAKING_ZONE)
            R.id.attackType_backBtn -> findNavController().navigate(R.id.action_attackTypeFragment_to_timeTypeFragment)
        }
    }

    private fun setData(attackType: AttackType) {
        GameValues.gameMoment.setAttackType(attackType)
        findNavController().navigate(R.id.action_attackTypeFragment_to_attackResultFragment)
    }
}