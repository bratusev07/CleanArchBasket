package ru.bratusev.basketfeature.presentation.games.diagrams

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

class VerticalView(context: Context, private val pointList1: ArrayList<Int>, private val pointList2: ArrayList<Int>) : View(context) {

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

    private fun getShotCount(second: Int, pointList: ArrayList<Int>) : Int{
        var count = 0
        for (point in pointList) {
            if(point == second) count++
        }
        return count
    }

    private fun fillData(canvas: Canvas){
        var y = 20f
        for (i in 1..24){
            canvas.drawLine(width.toFloat()/10, y-verticalStep/2, width - width.toFloat()/10, y-verticalStep/2, paintBack)
            canvas.drawLine(centerX, y, centerX+getShotCount(i, pointList1)*sectionWidth, y, paintTeam)
            canvas.drawLine(centerX, y, centerX-getShotCount(i, pointList2)*sectionWidth, y, paintEnemy)
            canvas.drawText(i.toString(), centerX+getShotCount(i, pointList1)*sectionWidth + 25f, y+paintText.textSize/3, paintText)
            canvas.drawText(i.toString(), centerX-getShotCount(i, pointList2)*sectionWidth - 25f - sectionWidth, y+paintText.textSize/3, paintText)
            y+=verticalStep
        }
        canvas.drawLine(width.toFloat()/10, y-verticalStep/2, width - width.toFloat()/10, y-verticalStep/2, paintBack)
    }

}
