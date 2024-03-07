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
import ru.bratusev.domain.models.GameMoment
import ru.bratusev.domain.models.TimeType

class TimeTypeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_time_type, container, false).also {
            val bundle = Bundle()
            it.findViewById<GridView>(R.id.timeType_gridView).adapter =
                PlayersGridAdapter(requireContext(), arrayListOf(12, 23, 24, 14, 21))
            it.findViewById<AppCompatButton>(R.id.timeType_OkBtn).setOnClickListener {
                bundle.putSerializable(
                    "GameMoment", (arguments?.getSerializable("GameMoment") as GameMoment)
                        .setTimeType(TimeType.TIME_24)
                        .setPlayer("Ванька")
                        .setSecond(14)
                )
                findNavController().navigate(
                    R.id.action_timeTypeFragment_to_attackTypeFragment,
                    bundle
                )
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