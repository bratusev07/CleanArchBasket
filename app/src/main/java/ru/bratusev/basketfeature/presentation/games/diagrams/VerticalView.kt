package ru.bratusev.basketfeature.presentation.games.diagrams

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import ru.bratusev.domain.models.VerticalPoint

class VerticalView(context: Context, private val points: ArrayList<VerticalPoint>) : View(context) {

    private val paintBack = Paint()
    private val paintText = Paint()
    private val paintTeam = Paint()
    private val paintEnemy = Paint()

    init {
        paintBack.color = Color.BLACK
        paintBack.alpha = 50
        paintBack.style = Paint.Style.STROKE
        paintBack.strokeWidth = 5f
    }

    init {
        paintTeam.strokeWidth = 20f
        paintTeam.color = Color.BLUE
    }

    init {
        paintText.color = Color.BLACK
        paintText.alpha = 150
        paintText.textSize = 30f
        paintText.isFakeBoldText = true
    }

    init {
        paintEnemy.strokeWidth = 20f
        paintEnemy.color = Color.CYAN
    }

    private val sectionWidth = 15f
    private val verticalStep = paintTeam.strokeWidth + 25f
    private var centerX = 0f

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        centerX = width / 2f
        fillData(canvas)
    }

    private fun fillData(canvas: Canvas){
        var y = 20f
        for (i in 1..points.size/2){
            canvas.drawLine(width.toFloat()/10, y-verticalStep/2, width - width.toFloat()/10, y-verticalStep/2, paintBack)
            canvas.drawLine(centerX, y, centerX+ points[i - 1].value *sectionWidth, y, paintTeam)
            canvas.drawText(i.toString(), centerX+ points[i - 1].value *sectionWidth + 25f, y+paintText.textSize/3, paintText)
            canvas.drawText(i.toString(), centerX- points[i - 1 + points.size/2].value *sectionWidth - 25f - sectionWidth, y+paintText.textSize/3, paintText)
            canvas.drawLine(centerX, y, centerX- points[i -1 + points.size/2].value *sectionWidth, y, paintEnemy)
            y+=verticalStep
        }
        canvas.drawLine(width.toFloat()/10, y-verticalStep/2, width - width.toFloat()/10, y-verticalStep/2, paintBack)
    }

}
