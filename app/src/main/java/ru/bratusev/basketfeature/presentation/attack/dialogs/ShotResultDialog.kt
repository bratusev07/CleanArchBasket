package ru.bratusev.basketfeature.presentation.attack.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.bratusev.basketfeature.R
import ru.bratusev.basketfeature.presentation.attack.GameValues
import ru.bratusev.domain.models.GameMoment
import ru.bratusev.domain.models.ShotResult

class ShotResultDialog(private val isFoul: Boolean = false, private val gameMoment: GameMoment = GameMoment(GameValues.gameId)) :
    BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.shot_result_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setBackgroundResource(R.drawable.rounded_top_corners)
        val id = if (isFoul) R.id.action_attackFoulFragment_to_timeFragment
        else R.id.action_attackFinishTypeFragment_to_timeFragment
        view.findViewById<AppCompatButton>(R.id.shotResult_miss).setOnClickListener {
            gameMoment.setIsHit(ShotResult.MISS)
            AcceptDialog(requireContext(), id, requireParentFragment()).show()
        }
        view.findViewById<AppCompatButton>(R.id.shotResult_score).setOnClickListener {
            gameMoment.setIsHit(ShotResult.HIT)
            AcceptDialog(requireContext(), id, requireParentFragment()).show()
        }
    }
}