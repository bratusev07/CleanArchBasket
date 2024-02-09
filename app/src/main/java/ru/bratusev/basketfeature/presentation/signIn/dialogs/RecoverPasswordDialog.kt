package ru.bratusev.basketfeature.presentation.signIn.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Space
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.bratusev.basketfeature.R

class RecoverPasswordDialog : BottomSheetDialogFragment() {

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
        view.findViewById<AppCompatButton>(R.id.recoverPass_btnRecover).setOnClickListener {
            Toast.makeText(requireContext(), "Пароль восстановлен", Toast.LENGTH_SHORT).show()
            //dismiss()
        }
    }
}