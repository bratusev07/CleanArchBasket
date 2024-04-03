package ru.bratusev.basketfeature.presentation.games.diagrams

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.view.View
import ru.bratusev.basketfeature.presentation.games.view.StatsFragment
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

class HexagonView(context: Context, private val messagesArray: ArrayList<String>, private val pointList1: ArrayList<StatsFragment.Point>, private val pointList2: ArrayList<StatsFragment.Point>) : View(context) {

    private val paintBack = Paint()
    private val paintText = Paint()
    private val paintBorder = Paint()
    private val paintTeamDot = Paint()
    private val paintTeamZone = Paint()
    private val paintEnemyDot = Paint()
    private val paintEnemyZone = Paint()

    init {
        paintBack.color = Color.BLACK
        paintBack.alpha = 50
        paintBack.style = Paint.Style.STROKE
        paintBack.strokeWidth = 5f
    }

    init {
        paintText.color = Color.BLACK
        paintText.alpha = 150
        paintText.textSize = 30f
        paintText.isFakeBoldText = true
    }

    init {
        paintBorder.color = Color.BLACK
        paintBorder.alpha = 150
        paintBorder.style = Paint.Style.STROKE
        paintBorder.strokeWidth = 3f
    }

    init {
        paintTeamDot.color = Color.BLUE
        paintTeamDot.style = Paint.Style.FILL
    }

    init {
        paintEnemyDot.color = Color.CYAN
        paintEnemyDot.style = Paint.Style.FILL
    }

    init {
        paintEnemyZone.color = Color.CYAN
        paintEnemyZone.alpha = 150
        paintEnemyZone.style = Paint.Style.FILL
        paintEnemyZone.strokeWidth = 5f
    }

    init {
        paintTeamZone.color = Color.BLUE
        paintTeamZone.alpha = 150
        paintTeamZone.style = Paint.Style.FILL
        paintTeamZone.strokeWidth = 5f
    }

    private val path = Path()

    private var centerX = 0f
    private var centerY = 0f
    private var radius = 0f

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        centerX = width / 2f
        centerY = height / 2f
        radius = min(width, height) / 3f
        drawBack(canvas)
        drawZone(canvas, pointList1)
        drawZone(canvas, pointList2, true)
    }

    private fun drawBack(canvas: Canvas) {
        path.reset()
        val angle = 2 * Math.PI / 6
        for (i in 0 until 6) {
            val x = centerX + radius * cos(i * angle).toFloat()
            val y = centerY + radius * sin(i * angle).toFloat()
            canvas.drawText(messagesArray[i], if(i!=0) x-70f else x, y, paintText)
            if (i != 0) path.lineTo(x, y)
            path.moveTo(x, y)
            path.lineTo(centerX, centerY)
        }
        path.close()
        canvas.drawCircle(centerX, centerY, radius, paintBack)
        canvas.drawCircle(centerX, centerY, radius / 2, paintBack)
        canvas.drawPath(path, paintBack)
    }

    private fun drawZone(canvas: Canvas, pointList: ArrayList<StatsFragment.Point>, isEnemy: Boolean = false) {
        val dot = if(isEnemy) paintEnemyDot else paintTeamDot
        val zone = if(isEnemy) paintEnemyZone else paintTeamZone

        for (i in 0 until 6) {
            val angle = 2 * Math.PI / 6
            pointList[i].x = centerX + radius * pointList[i].value * cos(i * angle).toFloat()
            pointList[i].y = centerY + radius * pointList[i].value * sin(i * angle).toFloat()
            canvas.drawCircle(pointList[i].x, pointList[i].y, 15f, dot)
        }
        val path = Path()
        path.reset()
        path.moveTo(pointList[0].x, pointList[0].y)
        for (point in pointList) {
            path.lineTo(point.x, point.y)
        }
        path.close()
        canvas.drawPath(path, zone)
        canvas.drawPath(path, paintBorder)
    }
}

class Point(var x: Float = 0f, var y: Float = 0f, val value: Float = 0f){
    override fun toString(): String {
        return "$x $y $value"
    }
}
