package ru.bratusev.basketfeature.presentation.attack.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.bratusev.basketfeature.R

class TeamFoulDialog : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.team_foul_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setBackgroundResource(R.drawable.rounded_top_corners)
        view.findViewById<AppCompatButton>(R.id.teamFoul_myTeam).setOnClickListener {
            PlayerPenaltyDialog().show(childFragmentManager, "PlayerPenalty")
        }
        view.findViewById<AppCompatButton>(R.id.teamFoul_enemyTeam).setOnClickListener {
            PlayerPenaltyDialog().show(childFragmentManager, "PlayerPenalty")
        }
    }
}