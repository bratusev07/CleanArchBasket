package ru.bratusev.basketfeature.presentation.signIn.view

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.bratusev.basketfeature.R
import ru.bratusev.basketfeature.presentation.signIn.dialogs.RecoverPasswordDialog
import ru.bratusev.domain.models.UserData

class SignInFragment : Fragment() {

    private val vm: SignInViewModel by viewModel<SignInViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_in, container, false).also {
            val passEt = it.findViewById<TextInputEditText>(R.id.signIn_passwordEt)
            val mailEt = it.findViewById<TextInputEditText>(R.id.signIn_mailEt)
            val passHint = it.findViewById<TextView>(R.id.signIn_passHint)
            val mailHint = it.findViewById<TextView>(R.id.signIn_mailHint)
            val progressBar = it.findViewById<ProgressBar>(R.id.signIn_progressBar)

            it.findViewById<TextView>(R.id.signIn_forgotPass).setOnClickListener {
                RecoverPasswordDialog(vm).show(childFragmentManager, "RecoverPassword")
            }
            it.findViewById<TextView>(R.id.signIn_text_registration).setOnClickListener {
                findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
            }

            it.findViewById<AppCompatButton>(R.id.signIn_button).setOnClickListener {
                vm.validateData(UserData(mail = mailEt.text.toString(), password = passEt.text.toString()))
            }

            vm.resultLive.observe(viewLifecycleOwner){
                findNavController().navigate(R.id.action_signInFragment_to_teamsFragment)
            }

            vm.hintPassLive.observe(viewLifecycleOwner){
                if(vm.hintPassLive.value == false) passHint.visibility = View.VISIBLE
                else passHint.visibility = View.INVISIBLE
            }

            vm.hintMailLive.observe(viewLifecycleOwner){
                if(vm.hintMailLive.value == false) {
                    checkEmail(mailEt.text.toString())
                    mailHint.visibility = View.VISIBLE
                }
                else mailHint.visibility = View.INVISIBLE
            }

            vm.isLoading.observe(viewLifecycleOwner){
                if (vm.isLoading.value == true) {
                    progressBar.visibility = View.VISIBLE
                } else {
                    progressBar.visibility = View.GONE
                }
            }

            vm.isError.observe(viewLifecycleOwner){
                if (vm.isError.value == true) {
                    Snackbar.make(requireView(), "Неверный логин или пароль", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun checkEmail(email: String) {
        if (containSpecialSymbols(email)){
            showMessage("Почтовый адрес не может содержать специальных сомволов")
            return
        } else if(email.isEmpty()){
            showMessage("Почтовый адрес не может быть пустым")
            return
        } else if(!email.contains("@")){
            showMessage("Почтовый адрес должен содержать @")
            return
        } else if(!email.split("@")[1].contains(".")){
            showMessage("Почтовый адрес должен содержать точку в имени сервера")
            return
        } else if(email.split("@")[0].isEmpty()){
            showMessage("Почтовый адрес должен содержать имя")
            return
        }
    }

    private fun containSpecialSymbols(email: String): Boolean{
        for (symbol in arrayOf("!", "#", "$", "%", "&", "~", "=", "‘")) {
            if (email.contains(symbol)) return true
        }
        return false
    }
    private fun showMessage(message: String){
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
    }
}