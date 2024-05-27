package ru.bratusev.basketfeature.presentation.teams.dialogs

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.SeekBar
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContentProviderCompat.requireContext
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import ru.bratusev.basketfeature.R
import ru.bratusev.basketfeature.presentation.teams.view.TeamsViewModel
import ru.bratusev.domain.models.Player
import ru.bratusev.domain.models.Team

class CreateTeamDialog(private val vm: TeamsViewModel) : BottomSheetDialogFragment(),
    SeekBar.OnSeekBarChangeListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.create_team_dialog, container, false)
    }
    private val namePattern = Regex("^.*(?=.{2,18})(?=.*[а-яА-я\\s]).*\$")
    private lateinit var textInputContainer: LinearLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setBackgroundResource(R.drawable.rounded_top_corners)
        val players = ArrayList<Player>()
        val nameInput = view.findViewById<TextInputEditText>(R.id.createTeamDialog_nameInput)
        textInputContainer = view.findViewById(R.id.createTeamDialog_inputContainer)
        val playerCounter = view.findViewById<SeekBar>(R.id.createTeamDialog_playerCounter)
        view.findViewById<AppCompatButton>(R.id.createTeamDialog_okBtn).setOnClickListener {
            val enteredTexts = mutableListOf<String>()
            for (i in 0 until textInputContainer.childCount) {
                val editText = textInputContainer.getChildAt(i) as EditText
                enteredTexts.add(editText.text.toString())
            }
            for ((index, text) in enteredTexts.withIndex()) {
                players.add(Player(number = text.toInt()))
            }
            val name = nameInput.text.toString()
            if(name.isNotEmpty()) {
                if(containSpecialSymbols(name)) showMessage("Название команды не может содержать специальных символов")
                else if (!vm.isFree(name)) showMessage("Данное имя уже используется")
                else if (name.length !in 2..20) showMessage("Название должно иметь от 2 до 20 символов")
                else {
                    vm.createTeam(Team(name = name))
                    dismiss()
                }
            } else showMessage("Название команды не может быть пустым")
        }
        playerCounter.setOnSeekBarChangeListener(this)
    }

    private fun showMessage(message: String){
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
    }

    private fun containSpecialSymbols(name: String): Boolean{
        for (symbol in arrayOf("!", "#", "$", "%", "&", "~", "=", "‘", "_")) {
            if (name.contains(symbol)) return true
        }
        return false
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        textInputContainer.removeAllViews()
        for (i in 0 until progress) {
            val editText = EditText(requireContext())
            editText.hint = "Введите номер"
            editText.inputType = InputType.TYPE_CLASS_NUMBER
            textInputContainer.addView(editText)
        }
    }
    override fun onStartTrackingTouch(seekBar: SeekBar?) {}
    override fun onStopTrackingTouch(seekBar: SeekBar?) {}
}
