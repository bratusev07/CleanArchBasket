package ru.bratusev.basketfeature.presentation.signUp.dialogs

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.bratusev.basketfeature.R
import ru.bratusev.basketfeature.presentation.signUp.view.SignUpViewModel
import ru.bratusev.domain.models.UserData

class MailConfirmationDialog(private val vm: SignUpViewModel, private val userData: UserData) : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.mail_confirmation_dialog, container, false)
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setBackgroundResource(R.drawable.rounded_top_corners)
        view.findViewById<AppCompatButton>(R.id.confMail_btnRecover).setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
            Toast.makeText(requireContext(), "Вам на почту высланна ссылка подтверждения", Toast.LENGTH_SHORT).show()
        }
    }
}