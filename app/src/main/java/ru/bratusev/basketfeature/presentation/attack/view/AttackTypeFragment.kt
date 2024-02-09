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

class AttackTypeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_attack_type, container, false).also {
            it.findViewById<AppCompatButton>(R.id.attackType_backBtn).setOnClickListener {
                findNavController().navigate(R.id.action_attackTypeFragment_to_timeTypeFragment)
            }
            it.findViewById<AppCompatButton>(R.id.attackType_btn1)
                .setOnClickListener { findNavController().navigate(R.id.action_attackTypeFragment_to_attackResultFragment) }
            it.findViewById<AppCompatButton>(R.id.attackType_btn2)
                .setOnClickListener { findNavController().navigate(R.id.action_attackTypeFragment_to_attackResultFragment) }
            it.findViewById<AppCompatButton>(R.id.attackType_btn3)
                .setOnClickListener { findNavController().navigate(R.id.action_attackTypeFragment_to_attackResultFragment) }
            it.findViewById<AppCompatButton>(R.id.attackType_btn4)
                .setOnClickListener { findNavController().navigate(R.id.action_attackTypeFragment_to_attackResultFragment) }
            it.findViewById<AppCompatButton>(R.id.attackType_btn5)
                .setOnClickListener { findNavController().navigate(R.id.action_attackTypeFragment_to_attackResultFragment) }
            it.findViewById<AppCompatButton>(R.id.attackType_btn6)
                .setOnClickListener { findNavController().navigate(R.id.action_attackTypeFragment_to_attackResultFragment) }
            requireActivity().onBackPressedDispatcher.addCallback(
                viewLifecycleOwner,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        findNavController().navigate(R.id.action_attackTypeFragment_to_timeTypeFragment)
                    }
                })
        }
    }
}