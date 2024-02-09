package ru.bratusev.basketfeature.presentation

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.bratusev.basketfeature.R

class MainActivity : AppCompatActivity() {

    private val vm: MainViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findNavController(R.id.nav_host_fragment)
    }
}