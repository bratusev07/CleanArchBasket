package ru.bratusev.basketfeature.presentation.games.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.bratusev.basketfeature.R
import ru.bratusev.basketfeature.presentation.games.adapter.GamesAdapter

class GamesFragment : Fragment() {

    private val vm: GameViewModel by viewModel<GameViewModel>()
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

            gameList.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            vm.getGames()

            vm.gameList.observe(viewLifecycleOwner) {
                gameList.adapter = GamesAdapter(vm.gameList.value!!, this, vm)
            }
        }
    }
}