package ru.bratusev.basketfeature.presentation.teams.dialogs

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.textfield.TextInputEditText
import ru.bratusev.basketfeature.R
import ru.bratusev.basketfeature.presentation.teams.view.TeamsViewModel

class UpdateTeamDialog(private val context: Context) {
    private val dialogView: View =
        LayoutInflater.from(context).inflate(R.layout.update_team_dialog, null)

    fun show(id: String, vm: TeamsViewModel) {
        val builder = AlertDialog.Builder(context)
        builder.setView(dialogView)
        val alertDialog = builder.create()
        alertDialog.show()
        alertDialog.findViewById<AppCompatButton>(R.id.updateTeamDialog_okBtn).setOnClickListener {
            val teamName = alertDialog.findViewById<TextInputEditText>(R.id.updateTeamDialog_nameInput).text.toString()
            if(teamName.isNotEmpty()){
                vm.updateTeam(teamName, id)
                alertDialog.dismiss()
            }
            else Toast.makeText(context, "Введите название команды", Toast.LENGTH_SHORT).show()
        }
    }
}