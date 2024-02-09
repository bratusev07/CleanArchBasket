package ru.bratusev.basketfeature.presentation.signUp.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.bratusev.basketfeature.R

class MailConfirmationDialog : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.mail_confirmation_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setBackgroundResource(R.drawable.rounded_top_corners)
        view.findViewById<AppCompatButton>(R.id.confMail_btnRecover).setOnClickListener {
            Toast.makeText(requireContext(), "Вы успешно зарегистрировались", Toast.LENGTH_SHORT)
                .show()
            findNavController().navigate(R.id.action_signUpFragment_to_teamsFragment)
            dismiss()
        }
    }
}