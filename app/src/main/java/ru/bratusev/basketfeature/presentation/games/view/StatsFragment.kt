package ru.bratusev.basketfeature.presentation.games.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.bratusev.basketfeature.R
import ru.bratusev.basketfeature.presentation.attack.GameValues
import ru.bratusev.basketfeature.presentation.games.diagrams.HexagonView
import ru.bratusev.basketfeature.presentation.games.diagrams.VerticalView
import ru.bratusev.domain.models.GameMoment
import ru.bratusev.domain.models.HexagonPoint

class StatsFragment : Fragment() {

    private val vm: StatsViewModel by viewModel<StatsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_actions, container, false).also {
            val frame1 = it.findViewById<FrameLayout>(R.id.stats_frame1)
            val frame2 = it.findViewById<FrameLayout>(R.id.stats_frame2)
            var hexagonView: HexagonView
            var verticalView: VerticalView

            vm.actionListLive.observe(viewLifecycleOwner) {
                hexagonView =
                    HexagonView(requireContext(), parseToHexagonPoints(vm.actionListLive.value))
                //verticalView = VerticalView(requireContext(), )

                frame1.addView(hexagonView)
                //frame2.addView(verticalView)
            }

            vm.getActions(gameId = GameValues.gameId)
        }
    }

    private fun parseToHexagonPoints(value: ArrayList<GameMoment>?): ArrayList<HexagonPoint> {
        val pointListHexagon = ArrayList<HexagonPoint>()
        val myTeamId = value?.get(0)?.teamId

        value?.forEach { action ->
            if (action.teamId == myTeamId) {
                pointListHexagon.add(
                    HexagonPoint(
                        attackType = action.attackType,
                        shotResult = action.shotResult,
                        isEnemy = false
                    )
                )
            } else {
                pointListHexagon.add(
                    HexagonPoint(
                        attackType = action.attackType,
                        shotResult = action.shotResult,
                        isEnemy = true
                    )
                )
            }
        }

        return pointListHexagon
    }

}