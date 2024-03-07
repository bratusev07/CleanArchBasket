package ru.bratusev.basketfeature.presentation.attack.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.bratusev.basketfeature.R
import ru.bratusev.basketfeature.presentation.attack.dialogs.FinishGameDialog
import ru.bratusev.domain.models.GameMoment
import ru.bratusev.domain.models.Loss
import ru.bratusev.domain.models.TimeZone
import java.lang.Exception

class TimeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_time, container, false).also {
            val gameMoment = try {
                (arguments?.getSerializable("GameMoment") as GameMoment)
            }catch (e: Exception){
                GameMoment()
            }
            try {
                Log.d("MyGameMoment", gameMoment.toString())
            }catch (_: Exception){}

            val bundle = Bundle()
            it.findViewById<AppCompatButton>(R.id.time_myTeam).setOnClickListener {
                gameMoment.setTeam("Моя команда").setTimeZone(TimeZone.TIME_2)
                bundle.putSerializable("GameMoment", gameMoment)
                findNavController().navigate(
                    R.id.action_timeFragment_to_attackStartFragment,
                    bundle
                )
            }
            it.findViewById<AppCompatButton>(R.id.time_enemyTeam).setOnClickListener {
                gameMoment.setTeam("Не моя команда").setTimeZone(TimeZone.TIME_2)
                bundle.putSerializable("GameMoment", gameMoment)
                findNavController().navigate(
                    R.id.action_timeFragment_to_attackStartFragment,
                    bundle
                )
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