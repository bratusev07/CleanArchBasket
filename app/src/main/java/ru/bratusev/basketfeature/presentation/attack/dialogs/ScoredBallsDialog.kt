package ru.bratusev.basketfeature.presentation.attack.dialogs

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.bratusev.basketfeature.R

class ScoredBallsDialog(private val ballCount: Int) : BottomSheetDialogFragment() {

    private var imageViews: Array<ImageView?> = arrayOfNulls(ballCount)
    private var pointCount = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.scored_balls_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setBackgroundResource(R.drawable.rounded_top_corners)
        val frame = view.findViewById<LinearLayout>(R.id.ball_frame)

            for (i in 0 until ballCount) {
                val imageView = ImageView(requireContext())
                imageView.setImageResource(R.drawable.button_ball)
                val layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
                )
                layoutParams.setMargins(20, 0, 20, 0)
                imageView.layoutParams = layoutParams
                imageView.setOnClickListener {
                    toggleColor(imageView)
                }
                frame.addView(imageView)
                imageViews[i] = imageView
            }

        view.findViewById<AppCompatButton>(R.id.scoredBalls_okBtn).setOnClickListener {
            AcceptDialog(requireContext(), R.id.action_attackFoulFragment_to_timeFragment, requireParentFragment()).show()
            dismiss()
        }
    }

    private fun toggleColor(imageView: ImageView) {
        if (imageView.tag == "orange") {
            pointCount--
            imageView.setImageResource(R.drawable.button_ball)
            imageView.tag = "transparent"
        } else {
            pointCount++
            imageView.setImageResource(R.drawable.button_ball_active)
            imageView.tag = "orange"
        }
    }

}