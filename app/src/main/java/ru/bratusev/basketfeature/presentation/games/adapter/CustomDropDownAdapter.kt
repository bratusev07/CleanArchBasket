package ru.bratusev.basketfeature.presentation.games.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import ru.bratusev.basketfeature.R
import ru.bratusev.domain.models.Team
import ru.bratusev.domain.models.TeamListResponse

class CustomDropDownAdapter(context: Context, private var listItemsTxt: ArrayList<TeamListResponse>) :
    BaseAdapter() {


    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val vh: ItemRowHolder
        if (convertView == null) {
            view = mInflater.inflate(R.layout.spinner_item, parent, false)
            vh = ItemRowHolder(view)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ItemRowHolder
        }

        vh.label.text = listItemsTxt[position].name
        return view
    }

    override fun getItem(position: Int): Any {
        return listItemsTxt[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return listItemsTxt.size
    }

    private class ItemRowHolder(row: View?) {
        val label: TextView
        init {
            this.label = row?.findViewById(R.id.txtDropDownLabel) as TextView
        }
    }
}