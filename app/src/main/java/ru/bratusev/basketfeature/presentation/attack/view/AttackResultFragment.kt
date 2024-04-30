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
import ru.bratusev.domain.models.ResultType

class AttackResultFragment : Fragment(), OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_attack_result, container, false).also {
            it.findViewById<AppCompatButton>(R.id.attackResult_btn1).setOnClickListener(this)
            it.findViewById<AppCompatButton>(R.id.attackResult_btn2).setOnClickListener(this)
            it.findViewById<AppCompatButton>(R.id.attackResult_btn3).setOnClickListener(this)
            it.findViewById<AppCompatButton>(R.id.attackResult_backBtn).setOnClickListener(this)
            requireActivity().onBackPressedDispatcher.addCallback(
                viewLifecycleOwner,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        findNavController().navigate(R.id.action_attackResultFragment_to_attackTypeFragment)
                    }
                })
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.attackResult_btn1 -> {
                GameValues.gameMoment.setResultType(ResultType.FOUL)
                findNavController().navigate(R.id.action_attackResultFragment_to_attackFoulFragment)
            }
            R.id.attackResult_btn2 -> {
                GameValues.gameMoment.setResultType(ResultType.SHOT)
                findNavController().navigate(R.id.action_attackResultFragment_to_attackFinishTypeFragment)
            }
            R.id.attackResult_btn3 -> {
                GameValues.gameMoment.setResultType(ResultType.LOSS)
                findNavController().navigate(R.id.action_attackResultFragment_to_attackLostFragment)
            }
            R.id.attackResult_backBtn -> findNavController().navigate(R.id.action_attackResultFragment_to_attackTypeFragment)
        }
    }
}