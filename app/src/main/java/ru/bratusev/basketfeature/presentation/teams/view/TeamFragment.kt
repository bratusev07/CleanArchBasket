package ru.bratusev.basketfeature.presentation.teams.view

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
import ru.bratusev.basketfeature.presentation.teams.adapter.TeamPlayerAdapter

class TeamFragment : Fragment() {

    private val vm: TeamViewModel by viewModel<TeamViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_team, container, false).also {
            val teamList = it.findViewById<RecyclerView>(R.id.teamPlayerList)
            it.findViewById<ImageView>(R.id.team_plus).setOnClickListener {}
            it.findViewById<ImageView>(R.id.team_setting).setOnClickListener {}
            it.findViewById<ImageView>(R.id.team_game).setOnClickListener {
                findNavController().navigate(R.id.action_teamFragment_to_gamesFragment)
            }
            it.findViewById<ImageView>(R.id.team_teams).setOnClickListener {
                findNavController().navigate(R.id.action_teamFragment_to_teamsFragment)
            }
            requireActivity().onBackPressedDispatcher.addCallback(
                viewLifecycleOwner,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        findNavController().navigate(R.id.action_teamFragment_to_teamsFragment)
                    }
                })

            teamList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            vm.getPlayersList()

            vm.playerList.observe(viewLifecycleOwner){
                teamList.adapter = TeamPlayerAdapter(vm.playerList.value!!, this, vm)
            }
        }
    }
}