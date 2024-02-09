package ru.bratusev.basketfeature.presentation.games

import android.widget.Spinner
import ru.bratusev.basketfeature.presentation.games.adapter.CustomDropDownAdapter

class SelectTeamPresenter {

    internal fun initSpinner(spinner: Spinner, list : List<String> = arrayListOf()) {
        val spinnerAdapter =
            CustomDropDownAdapter(spinner.context, list)
        spinner.adapter = spinnerAdapter
    }
}