package ru.bratusev.basketfeature.presentation.games.diagrams

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.HorizontalScrollView
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import ru.bratusev.domain.models.GameMoment

class TableView(context: Context, attrs: AttributeSet?) : HorizontalScrollView(context, attrs) {

    private val tableLayout: TableLayout
    private lateinit var textView: TextView
    private val headers = arrayListOf(
        "Индекс",
        "Четверть",
        "Тип владения",
        "Время",
        "Тип атаки",
        "Результат атаки",
        "Способ завершения",
        "Попадание/промах",
        "Тип фола",
        "Результат фола",
        "Способ потери",
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
            textView = TextView(context)
            textView.text = header
            headerRow.addView(textView)
        }
        tableLayout.addView(headerRow)

        for (data in dataList) {
            val dataRow = TableRow(context)

            textView = TextView(context)
            textView.text = data.index.toString()
            dataRow.addView(textView)

            textView = TextView(context)
            textView.text = data.quater.toString()
            dataRow.addView(textView)

            textView = TextView(context)
            textView.text = data.typeOfPossession
            dataRow.addView(textView)

            textView = TextView(context)
            textView.text = data.time.toString()
            dataRow.addView(textView)

            textView = TextView(context)
            textView.text = data.attackType
            dataRow.addView(textView)

            textView = TextView(context)
            textView.text = data.resultType
            dataRow.addView(textView)

            textView = TextView(context)
            textView.text = data.shot
            dataRow.addView(textView)

            textView = TextView(context)
            textView.text = data.shotResult
            dataRow.addView(textView)

            textView = TextView(context)
            textView.text = data.foulType
            dataRow.addView(textView)

            textView = TextView(context)
            textView.text = data.foulResult.toString()
            dataRow.addView(textView)

            textView = TextView(context)
            textView.text = data.loss
            dataRow.addView(textView)

            tableLayout.addView(dataRow)
        }
    }
}