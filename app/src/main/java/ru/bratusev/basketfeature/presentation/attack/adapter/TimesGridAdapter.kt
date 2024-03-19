package ru.bratusev.basketfeature.presentation.attack.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import ru.bratusev.basketfeature.R
import ru.bratusev.domain.models.TimeZone

class TimesGridAdapter(private val context: Context, private val numbers: ArrayList<Int>) :
    BaseAdapter() {

    private var timeZone = TimeZone.TIME_1

    override fun getCount(): Int {
        return numbers.size
    }

    override fun getItem(position: Int): Any {
        return numbers[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView
        if (convertView == null) {
            val inflater = LayoutInflater.from(context)
            convertView = inflater.inflate(R.layout.grid_item, parent, false)
        }
        val tv = convertView?.findViewById<TextView>(R.id.textView)
        tv?.text = "0${numbers[position]}"

        convertView?.setOnClickListener {
            when (numbers[position]) {
                1 -> {
                    timeZone = TimeZone.TIME_1
                    tv?.setBackgroundColor(context.getColor(R.color.orange))
                }

                2 -> {
                    timeZone = TimeZone.TIME_2
                    tv?.setBackgroundColor(context.getColor(R.color.orange))
                }

                3 -> {
                    timeZone = TimeZone.TIME_3
                    tv?.setBackgroundColor(context.getColor(R.color.orange))
                }

                4 -> {
                    timeZone = TimeZone.TIME_4
                    tv?.setBackgroundColor(context.getColor(R.color.orange))
                }
            }
        }
        return convertView
    }

    fun getTimeZone(): TimeZone {
        return timeZone
    }
}