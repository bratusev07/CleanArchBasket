package ru.bratusev.basketfeature.presentation.attack.dialogs

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.bratusev.basketfeature.R

class AcceptDialog(
    private val context: Context,
    private val id: Int,
    private val fragment: Fragment
) {
    private val dialogView: View =
        LayoutInflater.from(context).inflate(R.layout.accept_dialog, null)

    fun show() {
        val builder = AlertDialog.Builder(context)
        builder.setView(dialogView)
        val alertDialog = builder.create()
        alertDialog.show()
        alertDialog.findViewById<TextView>(R.id.acceptDialog_okBtn).setOnClickListener {
            fragment.findNavController().navigate(id)
            alertDialog.dismiss()
        }
    }
}