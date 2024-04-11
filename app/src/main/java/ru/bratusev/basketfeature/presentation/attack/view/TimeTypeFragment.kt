package ru.bratusev.basketfeature.presentation.attack.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.GridView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import android.widget.ViewFlipper
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.bratusev.basketfeature.R
import ru.bratusev.basketfeature.presentation.attack.GameValues
import ru.bratusev.basketfeature.presentation.attack.GameValues.isEnemy
import ru.bratusev.basketfeature.presentation.attack.adapter.PlayersGridAdapter
import ru.bratusev.domain.models.Player

class TimeTypeFragment : Fragment(), SeekBar.OnSeekBarChangeListener {

    private var second: Int = 1
    private lateinit var secondsTextView: TextView
    private lateinit var timeFlipper: ViewFlipper
    private var timeType = 14
    private lateinit var seekBar: SeekBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_time_type, container, false).also {
            secondsTextView = it.findViewById(R.id.secondOfAttack_textView)
            timeFlipper = it.findViewById(R.id.timeTake_flipper)
            val players = if (!isEnemy) GameValues.myPlayers else GameValues.enemyPlayers
            it.findViewById<AppCompatButton>(R.id.timeModel14_button).setOnClickListener {
                timeFlipper.showPrevious()
                timeType = 14
                seekBar.max = 14
            }
            it.findViewById<AppCompatButton>(R.id.timeModel24_button).setOnClickListener {
                timeFlipper.showNext()
                timeType = 24
                seekBar.max = 24
            }
            seekBar = it.findViewById(R.id.secondOfAttack_seekbar)
            seekBar.setOnSeekBarChangeListener(this)
            val grid = it.findViewById<GridView>(R.id.timeType_gridView)
            grid.adapter =
                PlayersGridAdapter(
                    requireContext(),
                    this,
                    players.filter { player -> player.isInGame } as ArrayList<Player>
                )
            it.findViewById<AppCompatButton>(R.id.timeType_OkBtn).setOnClickListener {
                GameValues.gameMoment
                    .setTimeType(timeType)
                    .setSecond(second)
                if(GameValues.gameMoment.passStory.size > 0) findNavController().navigate(R.id.action_timeTypeFragment_to_attackTypeFragment)
                else Toast.makeText(requireContext(), "Укажите игрока", Toast.LENGTH_SHORT).show()
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
        secondsTextView.text = progress.toString()
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
    }
}