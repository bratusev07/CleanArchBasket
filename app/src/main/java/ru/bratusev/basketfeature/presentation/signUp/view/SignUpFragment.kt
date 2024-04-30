package ru.bratusev.basketfeature.presentation.signUp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.bratusev.basketfeature.R
import ru.bratusev.basketfeature.presentation.signUp.dialogs.MailConfirmationDialog
import ru.bratusev.domain.models.UserData

class SignUpFragment : Fragment() {

    private val vm: SignUpViewModel by viewModel<SignUpViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false).also {
            val mailEt = it.findViewById<TextInputEditText>(R.id.signUp_mailEt)
            val passEt = it.findViewById<TextInputEditText>(R.id.signUp_passEt)
            val mailHint = it.findViewById<TextView>(R.id.singUp_mailHint)
            val passHint = it.findViewById<TextView>(R.id.singUp_passHint)

            it.findViewById<AppCompatButton>(R.id.signUp_button).setOnClickListener {
                vm.validateData(UserData(mail = mailEt.text.toString(), password = passEt.text.toString()))
            }

            it.findViewById<TextView>(R.id.signUp_have_acc_text).setOnClickListener {
                findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
            }

            vm.resultLive.observe(viewLifecycleOwner){
                MailConfirmationDialog().show(childFragmentManager, "MailConfirmation")
            }

            vm.hintPassLive.observe(viewLifecycleOwner){
                if(vm.hintPassLive.value == false) passHint.visibility = View.VISIBLE
                else passHint.visibility = View.INVISIBLE
            }

            vm.hintMailLive.observe(viewLifecycleOwner){
                if(vm.hintMailLive.value == false) mailHint.visibility = View.VISIBLE
                else mailHint.visibility = View.INVISIBLE
            }

            requireActivity().onBackPressedDispatcher.addCallback(
                viewLifecycleOwner,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
                    }
                })
        }
    }

}