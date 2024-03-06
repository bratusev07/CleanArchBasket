package ru.bratusev.basketfeature.presentation.teams.dialogs

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText
import ru.bratusev.basketfeature.R
import ru.bratusev.basketfeature.presentation.teams.view.TeamViewModel
import ru.bratusev.domain.models.Player

class UpdatePlayerDialog(
    private val vm: TeamViewModel,
    private val player: Player,
    private val index: Int
) : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.update_player_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setBackgroundResource(R.drawable.rounded_top_corners)
        val nameInput = view.findViewById<TextInputEditText>(R.id.updatePlayerDialog_nameInput)
        val surnameInput =
            view.findViewById<TextInputEditText>(R.id.updatePlayerDialog_surnameInput)
        val lastnameInput =
            view.findViewById<TextInputEditText>(R.id.updatePlayerDialog_lastnameInput)
        val numberInput = view.findViewById<TextInputEditText>(R.id.updatePlayerDialog_numberInput)

        nameInput.text = Editable.Factory.getInstance().newEditable(player.name)
        surnameInput.text = Editable.Factory.getInstance().newEditable(player.lastName)
        lastnameInput.text = Editable.Factory.getInstance().newEditable(player.surname)
        numberInput.text = Editable.Factory.getInstance().newEditable(player.number.toString())

        view.findViewById<AppCompatButton>(R.id.updatePlayerDialog_okBtn).setOnClickListener {
            vm.updatePlayer(
                Player(
                    name = nameInput.text.toString(),
                    number = numberInput.text.toString().toInt(),
                    surname = surnameInput.text.toString(),
                    lastName = lastnameInput.text.toString()
                ),
                index
            )
            dismiss()
        }
    }

}
