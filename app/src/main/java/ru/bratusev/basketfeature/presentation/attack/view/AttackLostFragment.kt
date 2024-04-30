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
import ru.bratusev.basketfeature.presentation.attack.dialogs.AcceptDialog
import ru.bratusev.domain.models.Loss

class AttackLostFragment : Fragment(), OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_attack_lost, container, false).also {
            it.findViewById<AppCompatButton>(R.id.attackLost_backBtn).setOnClickListener(this)
            it.findViewById<AppCompatButton>(R.id.attackLost_btn1).setOnClickListener(this)
            it.findViewById<AppCompatButton>(R.id.attackLost_btn2).setOnClickListener(this)
            it.findViewById<AppCompatButton>(R.id.attackLost_btn3).setOnClickListener(this)
            it.findViewById<AppCompatButton>(R.id.attackLost_btn4).setOnClickListener(this)

            requireActivity().onBackPressedDispatcher.addCallback(
                viewLifecycleOwner,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        findNavController().navigate(R.id.action_attackLostFragment_to_attackResultFragment)
                    }
                })
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.attackLost_btn1 -> setData(Loss.PASS_LOSS)
            R.id.attackLost_btn2 -> setData(Loss.TECHNICAL_LOSS)
            R.id.attackLost_btn3 -> setData(Loss.FOUL_IN_ATTACK)
            R.id.attackLost_btn4 -> setData(Loss.TACTICAL_LOSS)
            R.id.attackLost_backBtn -> findNavController().navigate(R.id.action_attackLostFragment_to_attackResultFragment)
        }
    }

    private fun setData(lossType: Loss){
        GameValues.gameMoment.setLoss(lossType)
        AcceptDialog(requireContext(), R.id.action_attackLostFragment_to_timeFragment, requireParentFragment()).show()
    }
}