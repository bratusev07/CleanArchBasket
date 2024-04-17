package ru.bratusev.basketfeature.presentation.games.diagrams

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.view.View
import ru.bratusev.basketfeature.R
import ru.bratusev.domain.models.HexagonPoint
import ru.bratusev.domain.models.ShotModel

class ShotPercent(context: Context, private val pointList: ArrayList<ShotModel>): View(context) {

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

        val boundX = (width*1.0).toInt()
        val boundY = (height*imgHeight/imgWidth).toInt()
        image?.let {
            it.setBounds(0, 0, width, boundY)
            it.draw(canvas)
        }

        // 1
        var result = calculatePointValue(1).split(":")
        canvas.drawText(result[0], 0.025f*boundX, 0.18f*boundY, paintText)
        canvas.drawText(result[1], 0.025f*boundX+10, 0.18f*boundY+30, paintTextSecond)

        // 2
        result = calculatePointValue(2).split(":")
        canvas.drawText(result[0], 0.14f*boundX, 0.73f*boundY, paintText)
        canvas.drawText(result[1], 0.14f*boundX+10, 0.73f*boundY+30, paintTextSecond)

        // 3
        result = calculatePointValue(3).split(":")
        canvas.drawText(result[0], 0.47f*boundX, 0.9f*boundY, paintText)
        canvas.drawText(result[1], 0.47f*boundX+10, 0.9f*boundY+30, paintTextSecond)

        // 4
        result = calculatePointValue(4).split(":")
        canvas.drawText(result[0], 0.8f*boundX, 0.73f*boundY, paintText)
        canvas.drawText(result[1], 0.8f*boundX+10, 0.73f*boundY+30, paintTextSecond)

        // 5
        result = calculatePointValue(5).split(":")
        canvas.drawText(result[0], 0.9f*boundX, 0.18f*boundY, paintText)
        canvas.drawText(result[1], 0.9f*boundX+10, 0.18f*boundY+30, paintTextSecond)

        // 6
        result = calculatePointValue(6).split(":")
        canvas.drawText(result[0], 0.17f*boundX, 0.24f*boundY, paintText)
        canvas.drawText(result[1], 0.17f*boundX+10, 0.24f*boundY+30, paintTextSecond)

        // 7
        result = calculatePointValue(7).split(":")
        canvas.drawText(result[0], 0.3f*boundX, 0.53f*boundY, paintText)
        canvas.drawText(result[1], 0.3f*boundX+10, 0.53f*boundY+30, paintTextSecond)

        // 8
        result = calculatePointValue(8).split(":")
        canvas.drawText(result[0], 0.47f*boundX, 0.66f*boundY, paintText)
        canvas.drawText(result[1], 0.47f*boundX+10, 0.66f*boundY+30, paintTextSecond)

        // 9
        result = calculatePointValue(9).split(":")
        canvas.drawText(result[0], 0.62f*boundX, 0.53f*boundY, paintText)
        canvas.drawText(result[1], 0.62f*boundX+10, 0.53f*boundY+30, paintTextSecond)

        // 10
        result = calculatePointValue(10).split(":")
        canvas.drawText(result[0], 0.765f*boundX, 0.24f*boundY, paintText)
        canvas.drawText(result[1], 0.765f*boundX+10, 0.24f*boundY+30, paintTextSecond)

        // 11
        result = calculatePointValue(11).split(":")
        canvas.drawText(result[0], 0.3f*boundX, 0.16f*boundY, paintText)
        canvas.drawText(result[1], 0.3f*boundX+10, 0.16f*boundY+30, paintTextSecond)

        // 12
        result = calculatePointValue(12).split(":")
        canvas.drawText(result[0], 0.47f*boundX, 0.37f*boundY, paintText)
        canvas.drawText(result[1], 0.47f*boundX+10, 0.37f*boundY+30, paintTextSecond)

        // 13
        result = calculatePointValue(13).split(":")
        canvas.drawText(result[0], 0.63f*boundX, 0.16f*boundY, paintText)
        canvas.drawText(result[1], 0.63f*boundX+10, 0.16f*boundY+30, paintTextSecond)

        // 14
        result = calculatePointValue(14).split(":")
        canvas.drawText(result[0], 0.47f*boundX, 0.09f*boundY, paintText)
        canvas.drawText(result[1], 0.47f*boundX+10, 0.09f*boundY+30, paintTextSecond)
    }

    private fun calculatePointValue(zoneNumber: Int): String{
        var countHit = 0
        var countShot = 0
        for (shotModel in pointList) {
            countShot++
            if(shotModel.zone == zoneNumber){
                countHit++
            }
        }
        return if(countShot == 0) "0/0:0%"
        else "$countHit/$countShot:${((countHit.toDouble()/countShot)*100).toInt()}%"
    }
}