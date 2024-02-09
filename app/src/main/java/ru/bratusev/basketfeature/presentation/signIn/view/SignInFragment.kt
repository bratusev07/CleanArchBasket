package ru.bratusev.basketfeature.presentation.signIn.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.bratusev.basketfeature.R
import ru.bratusev.basketfeature.presentation.signIn.dialogs.RecoverPasswordDialog

class SignInFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_in, container, false).also {
            it.findViewById<AppCompatButton>(R.id.signIn_button).setOnClickListener {
                findNavController().navigate(R.id.action_signInFragment_to_teamsFragment)
            }
            it.findViewById<TextView>(R.id.signIn_forgotPass).setOnClickListener {
                RecoverPasswordDialog().show(childFragmentManager, "RecoverPassword")
            }
            it.findViewById<TextView>(R.id.signIn_text_registration).setOnClickListener {
                findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
            }
        }
    }
}