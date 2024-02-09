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
import ru.bratusev.basketfeature.presentation.attack.dialogs.ShotResultDialog

class AttackFinishTypeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_attack_finish_type, container, false).also {
            it.findViewById<AppCompatButton>(R.id.finishType_backBtn)
                .setOnClickListener { findNavController().navigate(R.id.action_attackFinishTypeFragment_to_attackResultFragment) }
            it.findViewById<AppCompatButton>(R.id.finishType_btn1)
                .setOnClickListener { ShotResultDialog().show(childFragmentManager, "ShotResult") }
            it.findViewById<AppCompatButton>(R.id.finishType_btn2)
                .setOnClickListener { ShotResultDialog().show(childFragmentManager, "ShotResult") }
            it.findViewById<AppCompatButton>(R.id.finishType_btn3)
                .setOnClickListener { ShotResultDialog().show(childFragmentManager, "ShotResult") }
            it.findViewById<AppCompatButton>(R.id.finishType_btn4)
                .setOnClickListener { ShotResultDialog().show(childFragmentManager, "ShotResult") }
            it.findViewById<AppCompatButton>(R.id.finishType_btn5)
                .setOnClickListener { ShotResultDialog().show(childFragmentManager, "ShotResult") }
            it.findViewById<AppCompatButton>(R.id.finishType_btn6)
                .setOnClickListener { ShotResultDialog().show(childFragmentManager, "ShotResult") }
            it.findViewById<AppCompatButton>(R.id.finishType_btn7)
                .setOnClickListener { ShotResultDialog().show(childFragmentManager, "ShotResult") }
            it.findViewById<AppCompatButton>(R.id.finishType_btn8)
                .setOnClickListener { ShotResultDialog().show(childFragmentManager, "ShotResult") }
            it.findViewById<AppCompatButton>(R.id.finishType_btn9)
                .setOnClickListener { ShotResultDialog().show(childFragmentManager, "ShotResult") }
            it.findViewById<AppCompatButton>(R.id.finishType_btn10)
                .setOnClickListener { ShotResultDialog().show(childFragmentManager, "ShotResult") }
            it.findViewById<AppCompatButton>(R.id.finishType_btn11)
                .setOnClickListener { ShotResultDialog().show(childFragmentManager, "ShotResult") }
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