package ru.bratusev.basketfeature.presentation.attack.dialogs

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.bratusev.basketfeature.R
import ru.bratusev.basketfeature.presentation.attack.GameValues
import java.text.SimpleDateFormat
import java.time.ZonedDateTime
import java.util.Date

class AcceptDialog(
    private val context: Context,
    private val id: Int,
    private val fragment: Fragment
) {
    private val dialogView: View =
        LayoutInflater.from(context).inflate(R.layout.accept_dialog, null)

    @SuppressLint("NewApi")
    fun show() {
        val builder = AlertDialog.Builder(context)
        builder.setView(dialogView)
        val alertDialog = builder.create()
        alertDialog.show()
        alertDialog.findViewById<TextView>(R.id.acceptDialog_okBtn).setOnClickListener {
            fragment.findNavController().navigate(id)
            try {
                GameValues.gameMoment.addIndex()
                GameValues.gameMoment.setCreateTime(ZonedDateTime.now().toInstant().toString())
            }catch (_: Exception){}
            alertDialog.dismiss()
        }
    }
}