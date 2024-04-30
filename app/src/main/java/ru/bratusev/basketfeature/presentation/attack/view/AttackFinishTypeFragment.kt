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
import ru.bratusev.basketfeature.presentation.attack.GameValues.gameMoment
import ru.bratusev.basketfeature.presentation.attack.dialogs.AcceptDialog
import ru.bratusev.basketfeature.presentation.attack.dialogs.ShotZoneDialog
import ru.bratusev.domain.models.Loss
import ru.bratusev.domain.models.Shot

class AttackFinishTypeFragment : Fragment(), OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_attack_finish_type, container, false).also {
            it.findViewById<AppCompatButton>(R.id.finishType_btn1).setOnClickListener(this)
            it.findViewById<AppCompatButton>(R.id.finishType_btn2).setOnClickListener(this)
            it.findViewById<AppCompatButton>(R.id.finishType_btn3).setOnClickListener(this)
            it.findViewById<AppCompatButton>(R.id.finishType_btn4).setOnClickListener(this)
            it.findViewById<AppCompatButton>(R.id.finishType_btn5).setOnClickListener(this)
            it.findViewById<AppCompatButton>(R.id.finishType_btn6).setOnClickListener(this)
            it.findViewById<AppCompatButton>(R.id.finishType_btn7).setOnClickListener(this)
            it.findViewById<AppCompatButton>(R.id.finishType_btn8).setOnClickListener(this)
            it.findViewById<AppCompatButton>(R.id.finishType_btn9).setOnClickListener(this)
            it.findViewById<AppCompatButton>(R.id.finishType_btn10).setOnClickListener(this)
            it.findViewById<AppCompatButton>(R.id.finishType_btn11).setOnClickListener(this)
            it.findViewById<AppCompatButton>(R.id.finishType_backBtn).setOnClickListener(this)
            requireActivity().onBackPressedDispatcher.addCallback(
                viewLifecycleOwner,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        findNavController().navigate(R.id.action_attackFinishTypeFragment_to_attackResultFragment)
                    }
                })
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.finishType_btn1 -> setData(Shot.DRIVES)
            R.id.finishType_btn2 -> setData(Shot.TRANSITION)
            R.id.finishType_btn3 -> setData(Shot.PULL_UP)
            R.id.finishType_btn4 -> setData(Shot.PNR_HANDLER)
            R.id.finishType_btn5 -> setData(Shot.CUTS)
            R.id.finishType_btn6 -> setData(Shot.HAND_OFF)
            R.id.finishType_btn7 -> setData(Shot.ISOLATION)
            R.id.finishType_btn8 -> setData(Shot.CATCH_SHOOT)
            R.id.finishType_btn9 -> setData(Shot.POST_UP)
            R.id.finishType_btn10 -> setData(Shot.PNR_ROLLER)
            R.id.finishType_btn11 -> setData(Shot.OFF_SCREEN)
            R.id.finishType_backBtn -> findNavController().navigate(R.id.action_attackFinishTypeFragment_to_attackResultFragment)
        }
    }

    private fun setData(shot: Shot){
        gameMoment.setShot(shot)
        ShotZoneDialog(gameMoment).show(childFragmentManager, "ShotZone")
    }

}