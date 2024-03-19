package ru.bratusev.basketfeature.presentation.attack.dialogs

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.bratusev.basketfeature.R
import ru.bratusev.domain.models.GameMoment

class ShotZoneDialog(private val gameMoment: GameMoment) : BottomSheetDialogFragment() {

    private lateinit var imageView: ImageView
    private var zoneNumber: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.shot_zone_dialog, container, false)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setBackgroundResource(R.drawable.rounded_top_corners)

        imageView = view.findViewById(R.id.img)
        imageView.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val x = event.x / imageView.width
                val y = event.y / imageView.height
                changeZone(calculateZone(x, y))
            }
            true
        }
    }

    private fun calculateZone(x: Float, y: Float): Int {
        if (x in 0.0..0.1 && y in 0.0..0.3) return 1
        else if (x in 0.0..0.2 && y in 0.3..1.0) return 2
        else if (x in 0.2..0.38 && y in 0.7..1.0) return 2
        else if (x in 0.38..0.62 && y in 0.77..1.0) return 3
        else if (x in 0.8..1.0 && y in 0.3..1.0) return 4
        else if (x in 0.62..0.8 && y in 0.7..1.0) return 4
        else if (x in 0.9..1.0 && y in 0.0..0.3) return 5
        else if (x in 0.1..0.25 && y in 0.0..0.45) return 6
        else if (x in 0.25..0.43 && y in 0.4..0.75) return 7
        else if (x in 0.42..0.58 && y in 0.5..0.77) return 8
        else if (x in 0.57..0.75 && y in 0.4..0.75) return 9
        else if (x in 0.75..1.0 && y in 0.0..0.45) return 10
        else if (x in 0.27..0.41 && y in 0.0..0.32) return 11
        else if (x in 0.42..0.58 && y in 0.25..0.5) return 12
        else if (x in 0.59..0.73 && y in 0.0..0.32) return 13
        else if (x in 0.4..0.6 && y in 0.0..0.25) return 14
        else return 0
    }

    private fun changeZone(zoneNumber: Int) {
        if(zoneNumber == this.zoneNumber && zoneNumber != 0) {
            ShotResultDialog(gameMoment = gameMoment.setZoneNumber(zoneNumber)).show(parentFragmentManager, "ShotResult")
            return
        }
        when (zoneNumber) {
            0 -> imageView.setBackgroundResource(R.drawable.zone_0)
            1 -> imageView.setBackgroundResource(R.drawable.zone_1)
            2 -> imageView.setBackgroundResource(R.drawable.zone_2)
            3 -> imageView.setBackgroundResource(R.drawable.zone_3)
            4 -> imageView.setBackgroundResource(R.drawable.zone_4)
            5 -> imageView.setBackgroundResource(R.drawable.zone_5)
            6 -> imageView.setBackgroundResource(R.drawable.zone_6)
            7 -> imageView.setBackgroundResource(R.drawable.zone_7)
            8 -> imageView.setBackgroundResource(R.drawable.zone_8)
            9 -> imageView.setBackgroundResource(R.drawable.zone_9)
            10 -> imageView.setBackgroundResource(R.drawable.zone_10)
            11 -> imageView.setBackgroundResource(R.drawable.zone_11)
            12 -> imageView.setBackgroundResource(R.drawable.zone_12)
            13 -> imageView.setBackgroundResource(R.drawable.zone_13)
            14 -> imageView.setBackgroundResource(R.drawable.zone_14)
        }
        this.zoneNumber = zoneNumber
    }
}