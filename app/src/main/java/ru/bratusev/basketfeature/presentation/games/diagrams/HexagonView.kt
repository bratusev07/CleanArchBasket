package ru.bratusev.basketfeature.presentation.games.diagrams

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.Log
import android.view.View
import ru.bratusev.domain.models.HexagonPoint
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

class HexagonView(context: Context, private val pointList: ArrayList<HexagonPoint>) : View(context) {

    private val paintBack = Paint()
    private val paintText = Paint()
    private val paintBorder = Paint()
    private val paintTeamDot = Paint()
    private val paintTeamZone = Paint()
    private val paintEnemyDot = Paint()
    private val paintEnemyZone = Paint()

    private val messageList = ArrayList<String>().also {
        it.add("BREAKING_PRESSURE")
        it.add("BREAKING_ZONE")
        it.add("EARLY_ATTACK")
        it.add("QUICK_BREAKAWAY")
        it.add("POSITIONAL_ATTACK")
        it.add("SECOND_CHANCE_ATTACK")
    }

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
        drawZone(canvas, pointList.filter { elem -> !elem.isEnemy } as ArrayList<HexagonPoint>)
        drawZone(canvas, pointList.filter { elem -> elem.isEnemy } as ArrayList<HexagonPoint>)
    }

    private fun drawBack(canvas: Canvas) {
        path.reset()
        val angle = 2 * Math.PI / 6
        for (i in 0 until 6) {
            val x = centerX + radius * cos(i * angle).toFloat()
            val y = centerY + radius * sin(i * angle).toFloat()
            canvas.drawText(messageList[i].toLowerCase(), if(i!=0) x-70f else x, y, paintText)
            if (i != 0) path.lineTo(x, y)
            path.moveTo(x, y)
            path.lineTo(centerX, centerY)
        }
        path.close()
        canvas.drawCircle(centerX, centerY, radius, paintBack)
        canvas.drawCircle(centerX, centerY, radius / 2, paintBack)
        canvas.drawPath(path, paintBack)
    }

    private fun drawZone(canvas: Canvas, points: ArrayList<HexagonPoint>) {
        val dot: Paint
        val zone: Paint

        try {
            if(points[0].isEnemy){
                dot = paintEnemyDot
                zone = paintEnemyZone
            }else{
                dot = paintTeamDot
                zone = paintTeamZone
            }
        }catch (e: Exception){
            return
        }

        for (i in 0 until 6) {
            val value = calculateValue(points, messageList[i])
            val angle = 2 * Math.PI / 6
            try {
                points[i].x = centerX + radius * value * cos(i * angle).toFloat()
                points[i].y = centerY + radius * value * sin(i * angle).toFloat()
                canvas.drawCircle(points[i].x, points[i].y, 15f, dot)
            }catch (e: Exception){}
        }
        val path = Path()
        path.reset()
        path.moveTo(points[0].x, points[0].y)
        for (point in points) {
            if((point.x + point.y) > 1) path.lineTo(point.x, point.y)
        }
        path.close()
        canvas.drawPath(path, zone)
        canvas.drawPath(path, paintBorder)
    }

    private fun calculateValue(pointList: ArrayList<HexagonPoint>, attackType: String): Float {
        var count = 0f
        for (hexagonPoint in pointList) {
            if(hexagonPoint.attackType == attackType) count++
        }
        return (count / pointList.size)
    }
}
