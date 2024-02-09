package ru.bratusev.basketfeature.presentation.attack.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import ru.bratusev.basketfeature.R

class PlayersGridAdapter(private val context: Context, private val numbers: List<Int>) :
    BaseAdapter() {
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

        convertView?.findViewById<TextView>(R.id.textView)?.text = numbers[position].toString()

        return convertView
    }
}