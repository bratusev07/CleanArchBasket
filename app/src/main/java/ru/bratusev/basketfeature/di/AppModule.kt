package ru.bratusev.basketfeature.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.bratusev.basketfeature.presentation.signIn.view.SignInViewModel
import ru.bratusev.basketfeature.presentation.signUp.view.SignUpViewModel
import ru.bratusev.basketfeature.presentation.teams.view.TeamViewModel
import ru.bratusev.basketfeature.presentation.teams.view.TeamsViewModel

val appModule = module {

    viewModel<SignInViewModel>{
        SignInViewModel(
            authorizeUseCase = get()
        )
    }

    viewModel<SignUpViewModel>{
        SignUpViewModel(
            registrationUseCase = get()
        )
    }

    viewModel<TeamsViewModel>{
        TeamsViewModel(
            getTeamsListUseCase = get(),
            removeTeamUseCase = get(),
            updateTeamUseCase = get(),
            createTeamUseCase = get()
        )
    }

    viewModel<TeamViewModel>{
        TeamViewModel(
            getPlayersListUseCase = get(),
            removePlayerUseCase = get(),
            updatePlayerUseCase = get(),
            createPlayerUseCase = get()
        )
    }
}