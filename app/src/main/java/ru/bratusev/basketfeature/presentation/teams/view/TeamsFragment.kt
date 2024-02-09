package ru.bratusev.basketfeature.presentation.teams.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import ru.bratusev.basketfeature.R
import ru.bratusev.basketfeature.presentation.teams.TeamsPresenter

class TeamsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_teams, container, false).also {
            val teamsList = it.findViewById<RecyclerView>(R.id.teamsList)
            it.findViewById<ImageView>(R.id.teams_plus).setOnClickListener {}
            it.findViewById<ImageView>(R.id.teams_setting).setOnClickListener {}
            it.findViewById<ImageView>(R.id.teams_game).setOnClickListener {
                findNavController().navigate(R.id.action_teamsFragment_to_gamesFragment)
            }
            requireActivity().onBackPressedDispatcher.addCallback(
                viewLifecycleOwner,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {}
                })

            val presenter = TeamsPresenter()
            presenter.initList(teamsList, this)
        }
    }
}