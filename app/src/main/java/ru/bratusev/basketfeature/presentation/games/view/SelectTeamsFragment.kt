package ru.bratusev.basketfeature.presentation.games.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.ImageView
import android.widget.Spinner
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.bratusev.basketfeature.R
import ru.bratusev.basketfeature.presentation.games.adapter.CustomDropDownAdapter
import java.io.Serializable
import java.util.Calendar


class SelectTeamsFragment : Fragment() {

    private val vm: SelectTeamsViewModel by viewModel<SelectTeamsViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_select_teams, container, false).also {
            val myTeam = it.findViewById<Spinner>(R.id.myTeam_spinner)
            val enemyTeam = it.findViewById<Spinner>(R.id.enemyTeam_spinner)
            val calendar: Calendar = Calendar.getInstance()
            it.findViewById<DatePicker>(R.id.selectTeam_DataPicker).maxDate = calendar.timeInMillis
            it.findViewById<AppCompatButton>(R.id.selectTeams_nextBtn).setOnClickListener {
                val bundle = Bundle()
                bundle.putSerializable("GameDate", calendar.time)
                bundle.putSerializable("GameMyTeam", myTeam.selectedItem as Serializable?)
                bundle.putSerializable("GameEnemyTeam", enemyTeam.selectedItem as Serializable?)
                findNavController().navigate(R.id.action_selectTeamsFragment_to_selectPlayersFragment, bundle)
            }
            it.findViewById<ImageView>(R.id.selectTeams_back).setOnClickListener {
                findNavController().navigate(R.id.action_selectTeamsFragment_to_gamesFragment)
            }
            requireActivity().onBackPressedDispatcher.addCallback(
                viewLifecycleOwner,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        findNavController().navigate(R.id.action_selectTeamsFragment_to_gamesFragment)
                    }
                })

            vm.getTeamsList()
            vm.teams.observe(viewLifecycleOwner){
                myTeam.adapter = CustomDropDownAdapter(requireContext(), vm.teams.value!!)
                enemyTeam.adapter = CustomDropDownAdapter(requireContext(), vm.teams.value!!)
            }
        }
    }
}