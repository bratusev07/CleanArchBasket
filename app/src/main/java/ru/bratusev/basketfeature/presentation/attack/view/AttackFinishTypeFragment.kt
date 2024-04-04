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
import ru.bratusev.basketfeature.presentation.attack.GameValues.gameMoment
import ru.bratusev.basketfeature.presentation.attack.dialogs.ShotZoneDialog
import ru.bratusev.domain.models.Shot

class AttackFinishTypeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_attack_finish_type, container, false).also {
            it.findViewById<AppCompatButton>(R.id.finishType_btn11)
                .setOnClickListener {
                    gameMoment.setShot(Shot.DRIVES)
                    ShotZoneDialog(gameMoment).show(childFragmentManager, "ShotZone")
                }
            it.findViewById<AppCompatButton>(R.id.finishType_btn1)
                .setOnClickListener {
                    gameMoment.setShot(Shot.TRANSITION)
                    ShotZoneDialog(gameMoment).show(childFragmentManager, "ShotZone")
                }
            it.findViewById<AppCompatButton>(R.id.finishType_btn2)
                .setOnClickListener {
                    gameMoment.setShot(Shot.PULL_UP)
                    ShotZoneDialog(gameMoment).show(childFragmentManager, "ShotZone")
                }
            it.findViewById<AppCompatButton>(R.id.finishType_btn3)
                .setOnClickListener {
                    gameMoment.setShot(Shot.PNR_HANDLER)
                    ShotZoneDialog(gameMoment).show(childFragmentManager, "ShotZone")
                }
            it.findViewById<AppCompatButton>(R.id.finishType_btn4)
                .setOnClickListener {
                    gameMoment.setShot(Shot.CUTS)
                    ShotZoneDialog(gameMoment).show(childFragmentManager, "ShotZone")
                }
            it.findViewById<AppCompatButton>(R.id.finishType_btn5)
                .setOnClickListener {
                    gameMoment.setShot(Shot.HAND_OFF)
                    ShotZoneDialog(gameMoment).show(childFragmentManager, "ShotZone")
                }
            it.findViewById<AppCompatButton>(R.id.finishType_btn6)
                .setOnClickListener {
                    gameMoment.setShot(Shot.ISOLATION)
                    ShotZoneDialog(gameMoment).show(childFragmentManager, "ShotZone")
                }
            it.findViewById<AppCompatButton>(R.id.finishType_btn7)
                .setOnClickListener {
                    gameMoment.setShot(Shot.CATCH_SHOOT)
                    ShotZoneDialog(gameMoment).show(childFragmentManager, "ShotZone")
                }
            it.findViewById<AppCompatButton>(R.id.finishType_btn8)
                .setOnClickListener {
                    gameMoment.setShot(Shot.POST_UP)
                    ShotZoneDialog(gameMoment).show(childFragmentManager, "ShotZone")
                }
            it.findViewById<AppCompatButton>(R.id.finishType_btn9)
                .setOnClickListener {
                    gameMoment.setShot(Shot.PNR_ROLLER)
                    ShotZoneDialog(gameMoment).show(childFragmentManager, "ShotZone")
                }
            it.findViewById<AppCompatButton>(R.id.finishType_btn10)
                .setOnClickListener {
                    gameMoment.setShot(Shot.OFF_SCREEN)
                    ShotZoneDialog(gameMoment).show(childFragmentManager, "ShotZone")
                }
            it.findViewById<AppCompatButton>(R.id.finishType_backBtn)
                .setOnClickListener {
                    findNavController().navigate(R.id.action_attackFinishTypeFragment_to_attackResultFragment)
                }
            requireActivity().onBackPressedDispatcher.addCallback(
                viewLifecycleOwner,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        findNavController().navigate(R.id.action_attackFinishTypeFragment_to_attackResultFragment)
                    }
                })
        }
    }
}