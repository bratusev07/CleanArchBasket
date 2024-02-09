package ru.bratusev.basketfeature.presentation.teams.dialogs

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import ru.bratusev.basketfeature.R

class DeletePlayerDialog(private val context: Context) {
    private val dialogView: View =
        LayoutInflater.from(context).inflate(R.layout.delete_player_dialog, null)

    fun show() {
        val builder = AlertDialog.Builder(context)
        builder.setView(dialogView)
        val alertDialog = builder.create()
        alertDialog.show()
        alertDialog.findViewById<TextView>(R.id.deletePlayerDialog_okBtn).setOnClickListener {
            alertDialog.dismiss()
        }
    }
}