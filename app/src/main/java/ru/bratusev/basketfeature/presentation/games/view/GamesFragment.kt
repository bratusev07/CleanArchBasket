package ru.bratusev.basketfeature.presentation.games.view

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
import ru.bratusev.basketfeature.presentation.games.GamesPresenter

class GamesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_games, container, false).also {
            val gameTeams = it.findViewById<ImageView>(R.id.game_teams)
            val gamePlus = it.findViewById<ImageView>(R.id.game_plus)
            val gameList = it.findViewById<RecyclerView>(R.id.recyclerGamesView)
            gameTeams.setOnClickListener {
                findNavController().navigate(R.id.action_gamesFragment_to_teamsFragment)
            }
            gamePlus.setOnClickListener {
                findNavController().navigate(R.id.action_gamesFragment_to_selectTeamsFragment)
            }
            requireActivity().onBackPressedDispatcher.addCallback(
                viewLifecycleOwner,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {}
                })

            val presenter = GamesPresenter()
            presenter.initList(gameList)
        }
    }
}