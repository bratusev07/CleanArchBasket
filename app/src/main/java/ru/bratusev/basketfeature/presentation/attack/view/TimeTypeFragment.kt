package ru.bratusev.basketfeature.presentation.attack.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.bratusev.basketfeature.R
import ru.bratusev.basketfeature.presentation.attack.adapter.PlayersGridAdapter

class TimeTypeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_time_type, container, false).also {
            it.findViewById<GridView>(R.id.timeType_gridView).adapter =
                PlayersGridAdapter(requireContext(), arrayListOf(12, 23, 24, 14, 21))
            it.findViewById<AppCompatButton>(R.id.timeType_OkBtn).setOnClickListener {
                findNavController().navigate(R.id.action_timeTypeFragment_to_attackTypeFragment)
            }
            it.findViewById<AppCompatButton>(R.id.timeType_BackBtn).setOnClickListener {
                findNavController().navigate(R.id.action_timeTypeFragment_to_attackStartFragment)
            }
            requireActivity().onBackPressedDispatcher.addCallback(
                viewLifecycleOwner,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        findNavController().navigate(R.id.action_timeTypeFragment_to_attackStartFragment)
                    }
                })
        }
    }
}