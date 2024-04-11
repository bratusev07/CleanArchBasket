package ru.bratusev.basketfeature.presentation.games.diagrams

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.view.View
import ru.bratusev.basketfeature.R
import ru.bratusev.domain.models.HexagonPoint

class ShotPercent(context: Context, private val pointList: ArrayList<HexagonPoint>): View(context) {

    private val paintText = Paint()
    private val paintTextSecond = Paint()
    private var image: Drawable? = null

    init {
        paintText.color = Color.BLACK
        paintText.style = Paint.Style.FILL_AND_STROKE
        paintText.textSize = 30f
    }

    init {
        paintTextSecond.color = Color.RED
        paintTextSecond.style = Paint.Style.FILL_AND_STROKE
        paintTextSecond.textSize = 30f
    }

    init {
        image = context.getDrawable(R.drawable.zone_0)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val imgWidth = 374f
        val imgHeight = 210f

        val boundX = (width*0.97).toInt()
        val boundY = (height*imgHeight/imgWidth).toInt()
        image?.let {
            it.setBounds(0, 0, width, boundY)
            it.draw(canvas)
        }

        // 1
        canvas.drawText("31/63", 0.025f*boundX, 0.18f*boundY, paintText)
        canvas.drawText("49%", 0.025f*boundX+10, 0.18f*boundY+30, paintTextSecond)

        // 2
        canvas.drawText("31/63", 0.14f*boundX, 0.73f*boundY, paintText)
        canvas.drawText("49%", 0.14f*boundX+10, 0.73f*boundY+30, paintTextSecond)

        // 3
        canvas.drawText("31/63", 0.47f*boundX, 0.9f*boundY, paintText)
        canvas.drawText("49%", 0.47f*boundX+10, 0.9f*boundY+30, paintTextSecond)

        // 4
        canvas.drawText("31/63", 0.8f*boundX, 0.73f*boundY, paintText)
        canvas.drawText("49%", 0.8f*boundX+10, 0.73f*boundY+30, paintTextSecond)

        // 5
        canvas.drawText("31/63", 0.9f*boundX, 0.18f*boundY, paintText)
        canvas.drawText("49%", 0.9f*boundX+10, 0.18f*boundY+30, paintTextSecond)

        // 6
        canvas.drawText("31/63", 0.17f*boundX, 0.24f*boundY, paintText)
        canvas.drawText("49%", 0.17f*boundX+10, 0.24f*boundY+30, paintTextSecond)

        // 7
        canvas.drawText("31/63", 0.3f*boundX, 0.53f*boundY, paintText)
        canvas.drawText("49%", 0.3f*boundX+10, 0.53f*boundY+30, paintTextSecond)

        // 8
        canvas.drawText("31/63", 0.47f*boundX, 0.66f*boundY, paintText)
        canvas.drawText("49%", 0.47f*boundX+10, 0.66f*boundY+30, paintTextSecond)

        // 9
        canvas.drawText("31/63", 0.62f*boundX, 0.53f*boundY, paintText)
        canvas.drawText("49%", 0.62f*boundX+10, 0.53f*boundY+30, paintTextSecond)

        // 10
        canvas.drawText("31/63", 0.765f*boundX, 0.24f*boundY, paintText)
        canvas.drawText("49%", 0.765f*boundX+10, 0.24f*boundY+30, paintTextSecond)

        // 11
        canvas.drawText("31/63", 0.3f*boundX, 0.16f*boundY, paintText)
        canvas.drawText("49%", 0.3f*boundX+10, 0.16f*boundY+30, paintTextSecond)

        // 12
        canvas.drawText("31/63", 0.47f*boundX, 0.37f*boundY, paintText)
        canvas.drawText("49%", 0.47f*boundX+10, 0.37f*boundY+30, paintTextSecond)

        // 13
        canvas.drawText("31/63", 0.63f*boundX, 0.16f*boundY, paintText)
        canvas.drawText("49%", 0.63f*boundX+10, 0.16f*boundY+30, paintTextSecond)

        // 14
        canvas.drawText("31/63", 0.47f*boundX, 0.09f*boundY, paintText)
        canvas.drawText("49%", 0.47f*boundX+10, 0.09f*boundY+30, paintTextSecond)
    }
}