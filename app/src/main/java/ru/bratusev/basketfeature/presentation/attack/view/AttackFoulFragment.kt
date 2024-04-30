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
import ru.bratusev.basketfeature.presentation.attack.dialogs.ScoredBallsDialog
import ru.bratusev.basketfeature.presentation.attack.dialogs.TeamFoulDialog
import ru.bratusev.domain.models.Foul

class AttackFoulFragment : Fragment(), OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_attack_foul, container, false).also {
            it.findViewById<AppCompatButton>(R.id.attackFoul_backBtn).setOnClickListener(this)
            it.findViewById<AppCompatButton>(R.id.attackFoul_btn1).setOnClickListener(this)
            it.findViewById<AppCompatButton>(R.id.attackFoul_btn2).setOnClickListener(this)
            it.findViewById<AppCompatButton>(R.id.attackFoul_btn3).setOnClickListener(this)
            it.findViewById<AppCompatButton>(R.id.attackFoul_btn4).setOnClickListener(this)
            it.findViewById<AppCompatButton>(R.id.attackFoul_btn5).setOnClickListener(this)
            requireActivity().onBackPressedDispatcher.addCallback(
                viewLifecycleOwner,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        findNavController().navigate(R.id.action_attackFoulFragment_to_attackResultFragment)
                    }
                })
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.attackFoul_btn1 -> setData(Foul.SHOT_1, 1)
            R.id.attackFoul_btn2 -> setData(Foul.SHOT_2, 2)
            R.id.attackFoul_btn3 -> setData(Foul.SHOT_3, 3)
            R.id.attackFoul_btn4 -> {
                GameValues.gameMoment.setFoul(Foul.NOT_PUNCHY)
                findNavController().navigate(R.id.action_attackFoulFragment_to_timeFragment)
            }
            R.id.attackFoul_btn5 -> {
                GameValues.gameMoment.setFoul(Foul.TECHNICAL)
                TeamFoulDialog().show(childFragmentManager, "TeamFoul")
            }
            R.id.attackFoul_backBtn -> findNavController().navigate(R.id.action_attackFoulFragment_to_attackResultFragment)
        }
    }

    private fun setData(foul: Foul, shotCount: Int) {
        GameValues.gameMoment.setFoul(foul)
        ScoredBallsDialog(shotCount).show(childFragmentManager, "ScoredBall")
    }
}