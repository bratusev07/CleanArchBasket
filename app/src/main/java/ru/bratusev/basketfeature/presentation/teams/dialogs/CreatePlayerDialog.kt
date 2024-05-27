package ru.bratusev.basketfeature.presentation.teams.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import ru.bratusev.basketfeature.R
import ru.bratusev.basketfeature.presentation.teams.view.TeamViewModel
import ru.bratusev.domain.models.Player

class CreatePlayerDialog(private val vm: TeamViewModel) : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.create_player_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setBackgroundResource(R.drawable.rounded_top_corners)
        val nameInput = view.findViewById<TextInputEditText>(R.id.createTeamDialog_nameInput)
        val surnameInput = view.findViewById<TextInputEditText>(R.id.createTeamDialog_surnameInput)
        val lastnameInput =
            view.findViewById<TextInputEditText>(R.id.createTeamDialog_lastnameInput)
        val numberInput = view.findViewById<TextInputEditText>(R.id.createTeamDialog_numberInput)
        view.findViewById<AppCompatButton>(R.id.createTeamDialog_okBtn).setOnClickListener {
            if(numberInput.text.toString().toInt() !in 1 .. 99) showMessage("Номер должен принадлежать промежутку от 1 до 99")
            else {
                vm.createPlayer(
                    Player(
                        name = nameInput.text.toString(),
                        number = numberInput.text.toString().toInt(),
                        surname = surnameInput.text.toString(),
                        lastName = lastnameInput.text.toString()
                    )
                )
                dismiss()
            }
        }
    }

    private fun showMessage(message: String){
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
    }

}
