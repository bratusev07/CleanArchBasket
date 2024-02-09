package ru.bratusev.basketfeature.presentation.games.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.bratusev.basketfeature.R
import ru.bratusev.basketfeature.presentation.games.SelectTeamPresenter
import java.util.Calendar


class SelectTeamsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_select_teams, container, false).also {
            it.findViewById<AppCompatButton>(R.id.selectTeams_nextBtn).setOnClickListener {
                findNavController().navigate(R.id.action_selectTeamsFragment_to_selectPlayersFragment)
            }
            it.findViewById<ImageView>(R.id.selectTeams_back).setOnClickListener {
                findNavController().navigate(R.id.action_selectTeamsFragment_to_gamesFragment)
            }
            val calendar: Calendar = Calendar.getInstance()
            it.findViewById<DatePicker>(R.id.selectTeam_DataPicker).maxDate = calendar.timeInMillis
            requireActivity().onBackPressedDispatcher.addCallback(
                viewLifecycleOwner,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        findNavController().navigate(R.id.action_selectTeamsFragment_to_gamesFragment)
                    }
                })

            val presenter = SelectTeamPresenter()
            presenter.initSpinner(
                it.findViewById(R.id.myTeam_spinner),
                arrayListOf("Химки", "ЦСКА", "Химки-1", "ЦСКА-1", "Химки-2", "ЦСКА-2")
            )
            presenter.initSpinner(
                it.findViewById(R.id.enemyTeam_spinner),
                arrayListOf("Химки", "ЦСКА", "Химки-1", "ЦСКА-1", "Химки-2", "ЦСКА-2")
            )
        }
    }
}