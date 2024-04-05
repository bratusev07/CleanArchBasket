package ru.bratusev.basketfeature.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.bratusev.basketfeature.presentation.attack.view.TimeViewModel
import ru.bratusev.basketfeature.presentation.games.view.GameViewModel
import ru.bratusev.basketfeature.presentation.games.view.SelectEnemyViewModel
import ru.bratusev.basketfeature.presentation.games.view.SelectPlayersViewModel
import ru.bratusev.basketfeature.presentation.games.view.SelectTeamsViewModel
import ru.bratusev.basketfeature.presentation.signIn.view.SignInViewModel
import ru.bratusev.basketfeature.presentation.signUp.view.SignUpViewModel
import ru.bratusev.basketfeature.presentation.teams.view.TeamViewModel
import ru.bratusev.basketfeature.presentation.teams.view.TeamsViewModel

val appModule = module {

    viewModel<SignInViewModel> {
        SignInViewModel(
            authorizeUseCase = get()
        )
    }

    viewModel<SignUpViewModel> {
        SignUpViewModel(
            registrationUseCase = get()
        )
    }

    viewModel<TeamsViewModel> {
        TeamsViewModel(
            getTeamsListUseCase = get(),
            removeTeamUseCase = get(),
            updateTeamUseCase = get(),
            createTeamUseCase = get()
        )
    }

    viewModel<TeamViewModel> {
        TeamViewModel(
            getPlayersListUseCase = get(),
            removePlayerUseCase = get(),
            updatePlayerUseCase = get(),
            createPlayerUseCase = get()
        )
    }

    viewModel<SelectPlayersViewModel> {
        SelectPlayersViewModel(
            getPlayersListUseCase = get()
        )
    }

    viewModel<SelectEnemyViewModel> {
        SelectEnemyViewModel(
            createGameUseCase = get(),
            getPlayersListUseCase = get(),
            getGameIdUseCase = get()
        )
    }

    viewModel<GameViewModel> {
        GameViewModel(
            getGameListUseCase = get(),
            getTeamByIdUseCase = get(),
            removeGameUseCase = get()
        )
    }

    viewModel<SelectTeamsViewModel> {
        SelectTeamsViewModel(
            getTeamsListUseCase = get()
        )
    }

    viewModel<TimeViewModel> {
        TimeViewModel(
            createActionUseCase = get()
        )
    }
}