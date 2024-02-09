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
import ru.bratusev.basketfeature.presentation.attack.dialogs.FinishGameDialog

class TimeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_time, container, false).also {
            it.findViewById<AppCompatButton>(R.id.time_myTeam).setOnClickListener {
                findNavController().navigate(R.id.action_timeFragment_to_attackStartFragment)
            }
            it.findViewById<AppCompatButton>(R.id.time_enemyTeam).setOnClickListener {
                findNavController().navigate(R.id.action_timeFragment_to_attackStartFragment)
            }
            it.findViewById<AppCompatButton>(R.id.time_finish).setOnClickListener {
                FinishGameDialog(requireContext(), requireParentFragment()).show()
            }
            requireActivity().onBackPressedDispatcher.addCallback(
                viewLifecycleOwner,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        FinishGameDialog(requireContext(), requireParentFragment()).show()
                    }
                })
        }
    }
}