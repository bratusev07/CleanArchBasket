package ru.bratusev.basketfeature.presentation.signIn.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText
import ru.bratusev.basketfeature.R
import ru.bratusev.basketfeature.presentation.signIn.view.SignInViewModel

class RecoverPasswordDialog(val vm: SignInViewModel) : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recover_password_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setBackgroundResource(R.drawable.rounded_top_corners)
        val mailEt = view.findViewById<TextInputEditText>(R.id.recoverPass_mailEt)
        val mailHint = view.findViewById<TextView>(R.id.recoverPass_mailHint)
        view.findViewById<AppCompatButton>(R.id.recoverPass_btnRecover).setOnClickListener {
            vm.recoverPassword(mailEt.text.toString())
        }

        vm.hintDialogMailLive.observe(viewLifecycleOwner){
            if(vm.hintDialogMailLive.value == true){
                Toast.makeText(requireContext(), "ссылка для восстановления пароля отправлена", Toast.LENGTH_LONG).show()
                dismiss()
            } else mailHint.visibility = View.VISIBLE
        }
    }
}