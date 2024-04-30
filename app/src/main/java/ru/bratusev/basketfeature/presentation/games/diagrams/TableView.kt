package ru.bratusev.basketfeature.presentation.games.diagrams

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.marginRight
import ru.bratusev.domain.models.GameMoment

class TableView(context: Context, attrs: AttributeSet?) : HorizontalScrollView(context, attrs) {

    private val tableLayout: TableLayout
    private val headers = arrayListOf(
        "  Индекс  ",
        "  Четверть  ",
        "  Тип владения  ",
        "  Время  ",
        "  Тип атаки  ",
        "  Результат атаки  ",
        "  Способ завершения  ",
        "  Попадание/промах  ",
        "  Тип фола  ",
        "  Результат фола  ",
        "  Способ потери  ",
    )
    private val attackStartMap = mapOf(
        "SELECTION_IN_DEFENCE" to "Подбор в защите",
        "INTERCEPTION" to "Перехват",
        "LIVE_BALL" to "Живой мяч",
        "DEAD_BALL" to "Мертвый мяч",
        "SELECTION_IN_ATTACK" to "Подбор в нападении"
    )
    private val attackTypeMap = mapOf(
        "QUICK_BREAKAWAY" to "Быстрый отрыв",
        "EARLY_ATTACK" to "Раннее нападение",
        "SECOND_CHANCE_ATTACK" to "Второгй шанс",
        "POSITIONAL_ATTACK" to "Позиционное нападение",
        "BREAKING_PRESSURE" to "Против прессинга",
        "BREAKING_ZONE" to "Против зоны"
    )
    private val resultTypeMap = mapOf(
        "FOUL" to "Фол",
        "SHOT" to "Бросок",
        "LOSS" to "Потеря"
    )
    private val shotTypeMap = mapOf(
        "DRIVES" to "",
        "ISOLATION" to "",
        "TRANSITION" to "",
        "CATCH_SHOOT" to "",
        "PULL_UP" to "",
        "POST_UP" to "",
        "PNR_HANDLER" to "",
        "PNR_ROLLER" to "",
        "CUTS" to "",
        "OFF_SCREEN" to "",
        "HAND_OFF" to ""
    )
    private val shotResultMap = mapOf(
        "MISS" to "Промах",
        "HIT" to "Попадание"
    )
    private val foulTypeMap = mapOf(
        "SHOT_1" to "1 бросок",
        "SHOT_2" to "2 броска",
        "SHOT_3" to "3 броска",
        "NOT_PUNCHY" to "Не пробивной",
        "TECHNICAL" to "Технический"
    )
    private val lossTypeMap = mapOf(
        "PASS_LOSS" to "Пас-потеря",
        "TECHNICAL_LOSS" to "Техническая потеря",
        "FOUL_IN_ATTACK" to "Фол в нападении",
        "TACTICAL_LOSS" to "Тактическая потеря",
    )

    init {
        tableLayout = TableLayout(context)
        addView(tableLayout)
    }

    fun setData(dataList: ArrayList<GameMoment>) {

        dataList.sortBy { it.index }
        tableLayout.removeAllViews()

        val headerRow = TableRow(context)
        headerRow.setBackgroundColor(Color.GRAY)

        for (header in headers) {
            addToDataRow(headerRow, header)
        }
        tableLayout.addView(headerRow)

        for (data in dataList) {
            val dataRow = TableRow(context)
            addToDataRow(dataRow, data.index.toString())
            addToDataRow(dataRow, data.quater.toString())
            addToDataRow(dataRow, attackStartMap[data.typeOfPossession] ?: "")
            addToDataRow(dataRow, data.time.toString())
            addToDataRow(dataRow, attackTypeMap[data.attackType] ?: "")
            addToDataRow(dataRow, resultTypeMap[data.resultType] ?: "")
            addToDataRow(dataRow, data.shot)
            addToDataRow(dataRow, shotResultMap[data.shotResult] ?: "")
            addToDataRow(dataRow, foulTypeMap[data.foulType] ?: "")
            addToDataRow(dataRow, data.foulResult.toString())
            addToDataRow(dataRow, lossTypeMap[data.loss] ?: "")
            tableLayout.addView(dataRow)
        }
    }

    private fun addToDataRow(dataRow: TableRow, data: String) {
        val textView = TextView(context)
        textView.gravity = Gravity.CENTER
        textView.text = data
        dataRow.addView(textView)
    }
}