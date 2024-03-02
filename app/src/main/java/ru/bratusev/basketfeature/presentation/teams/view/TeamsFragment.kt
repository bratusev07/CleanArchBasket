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
import ru.bratusev.basketfeature.presentation.teams.adapter.TeamsAdapter
import ru.bratusev.basketfeature.presentation.teams.dialogs.CreateTeamDialog

class TeamsFragment : Fragment() {

    private val vm: TeamsViewModel by viewModel<TeamsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_teams, container, false).also {
            val teamsList = it.findViewById<RecyclerView>(R.id.teamsList)
            it.findViewById<ImageView>(R.id.teams_plus).setOnClickListener {
                CreateTeamDialog(vm).show(childFragmentManager, "CreateTeam")
            }
            it.findViewById<ImageView>(R.id.teams_setting).setOnClickListener {}
            it.findViewById<ImageView>(R.id.teams_game).setOnClickListener {
                findNavController().navigate(R.id.action_teamsFragment_to_gamesFragment)
            }
            requireActivity().onBackPressedDispatcher.addCallback(
                viewLifecycleOwner,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {}
                })

            teamsList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            vm.getTeamsList()

            vm.teamsLive.observe(viewLifecycleOwner){
                teamsList.adapter = TeamsAdapter(vm.teamsLive.value, this, vm)
            }
        }
    }
}