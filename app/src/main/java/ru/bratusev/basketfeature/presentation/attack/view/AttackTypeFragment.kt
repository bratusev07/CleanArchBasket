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
import ru.bratusev.domain.models.AttackType
import ru.bratusev.domain.models.GameMoment

class AttackTypeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_attack_type, container, false).also {
            val bundle = Bundle()
            it.findViewById<AppCompatButton>(R.id.attackType_backBtn).setOnClickListener {
                findNavController().navigate(R.id.action_attackTypeFragment_to_timeTypeFragment)
            }
            it.findViewById<AppCompatButton>(R.id.attackType_btn1)
                .setOnClickListener {
                    bundle.putSerializable(
                        "GameMoment",
                        (arguments?.getSerializable("GameMoment") as GameMoment).setAttackType(
                            AttackType.QUICK_BREAKAWAY
                        )
                    )
                    findNavController().navigate(
                        R.id.action_attackTypeFragment_to_attackResultFragment,
                        bundle
                    )
                }
            it.findViewById<AppCompatButton>(R.id.attackType_btn2)
                .setOnClickListener {
                    bundle.putSerializable(
                        "GameMoment",
                        (arguments?.getSerializable("GameMoment") as GameMoment).setAttackType(
                            AttackType.EARLY_ATTACK
                        )
                    )
                    findNavController().navigate(
                        R.id.action_attackTypeFragment_to_attackResultFragment,
                        bundle
                    )
                }
            it.findViewById<AppCompatButton>(R.id.attackType_btn3)
                .setOnClickListener {
                    bundle.putSerializable(
                        "GameMoment",
                        (arguments?.getSerializable("GameMoment") as GameMoment).setAttackType(
                            AttackType.SECOND_CHANCE_ATTACK
                        )
                    )
                    findNavController().navigate(
                        R.id.action_attackTypeFragment_to_attackResultFragment,
                        bundle
                    )
                }
            it.findViewById<AppCompatButton>(R.id.attackType_btn4)
                .setOnClickListener {
                    bundle.putSerializable(
                        "GameMoment",
                        (arguments?.getSerializable("GameMoment") as GameMoment).setAttackType(
                            AttackType.POSITIONAL_ATTACK
                        )
                    )
                    findNavController().navigate(
                        R.id.action_attackTypeFragment_to_attackResultFragment,
                        bundle
                    )
                }
            it.findViewById<AppCompatButton>(R.id.attackType_btn5)
                .setOnClickListener {
                    bundle.putSerializable(
                        "GameMoment",
                        (arguments?.getSerializable("GameMoment") as GameMoment).setAttackType(
                            AttackType.BREAKING_PRESSURE
                        )
                    )
                    findNavController().navigate(
                        R.id.action_attackTypeFragment_to_attackResultFragment,
                        bundle
                    )
                }
            it.findViewById<AppCompatButton>(R.id.attackType_btn6)
                .setOnClickListener {
                    bundle.putSerializable(
                        "GameMoment",
                        (arguments?.getSerializable("GameMoment") as GameMoment).setAttackType(
                            AttackType.BREAKING_ZONE
                        )
                    )
                    findNavController().navigate(
                        R.id.action_attackTypeFragment_to_attackResultFragment,
                        bundle
                    )
                }
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