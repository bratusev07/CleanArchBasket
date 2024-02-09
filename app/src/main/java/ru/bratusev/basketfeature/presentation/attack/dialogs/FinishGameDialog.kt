package ru.bratusev.basketfeature.presentation.attack.dialogs

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.bratusev.basketfeature.R

class FinishGameDialog(private val context: Context, private val fragment: Fragment) {
    private val dialogView: View =
        LayoutInflater.from(context).inflate(R.layout.finish_game_dialog, null)

    fun show() {
        val builder = AlertDialog.Builder(context)
        builder.setView(dialogView)
        val alertDialog = builder.create()
        alertDialog.show()
        alertDialog.findViewById<TextView>(R.id.finishGameDialog_okBtn).setOnClickListener {
            fragment.findNavController().navigate(R.id.action_timeFragment_to_gamesFragment)
            alertDialog.dismiss()
        }
        alertDialog.findViewById<AppCompatButton>(R.id.finishGameDialog_dismissBtn)
            .setOnClickListener {
                alertDialog.dismiss()
            }
    }
}