package ru.bratusev.basketfeature.presentation.attack.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import ru.bratusev.basketfeature.R

class TimesGridAdapter(private val context: Context, private val numbers: ArrayList<Int>) :
    BaseAdapter() {

    private var timeZone = 1

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
                    timeZone = 1
                    tv?.setBackgroundColor(context.getColor(R.color.orange))
                }

                2 -> {
                    timeZone = 2
                    tv?.setBackgroundColor(context.getColor(R.color.orange))
                }

                3 -> {
                    timeZone = 3
                    tv?.setBackgroundColor(context.getColor(R.color.orange))
                }

                4 -> {
                    timeZone = 4
                    tv?.setBackgroundColor(context.getColor(R.color.orange))
                }
            }
        }
        return convertView
    }

    fun getTimeZone(): Int {
        return timeZone
    }
}