package ru.bratusev.basketfeature.presentation.attack.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.SeekBar
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.bratusev.basketfeature.R
import ru.bratusev.basketfeature.presentation.attack.adapter.PlayersGridAdapter
import ru.bratusev.domain.models.GameMoment
import ru.bratusev.domain.models.Player
import ru.bratusev.domain.models.TimeType

class TimeTypeFragment : Fragment(), SeekBar.OnSeekBarChangeListener {

    private var second: Int = 1
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_time_type, container, false).also {
            val bundle = Bundle()
            (it.findViewById<SeekBar>(R.id.secondOfAttack_seekbar)).setOnSeekBarChangeListener(this)
            it.findViewById<GridView>(R.id.timeType_gridView).adapter =
                PlayersGridAdapter(
                    requireContext(),
                    arrayListOf(
                        Player(number = 12),
                        Player(number = 21),
                        Player(number = 34),
                        Player(number = 45),
                        Player(number = 62)
                    )
                )
            it.findViewById<AppCompatButton>(R.id.timeType_OkBtn).setOnClickListener {
                bundle.putSerializable(
                    "GameMoment", (arguments?.getSerializable("GameMoment") as GameMoment)
                        .setTimeType(TimeType.TIME_24)
                        .setPlayer("Ванька")
                        .setSecond(second)
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

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        second = progress
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
    }
}