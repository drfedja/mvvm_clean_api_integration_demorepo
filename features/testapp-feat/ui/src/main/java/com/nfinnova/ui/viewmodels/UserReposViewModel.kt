package com.nfinnova.ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.nfinnova.core_ui.BaseViewModel
import com.nfinnova.core_ui.screen_state.ScreenState
import com.nfinnova.domain.models.UserRepository
import com.nfinnova.domain.usecases.GetUserRepositoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserReposViewModel @Inject constructor(
    val getUserRepositoriesUseCase: GetUserRepositoriesUseCase
): BaseViewModel<UserReposViewModel.ViewState>() {

    override fun getInitialState(): ViewState = ViewState()

    init {
        viewModelScope.launch {
            getUserRepositoriesUseCase.invoke(USER_NAME).onSuccess { userRepositories ->
                reduce(
                    userRepoList = userRepositories,
                    screenState = ScreenState.Success
                )
            }.onFailure {
                reduce(screenState = ScreenState.Failure(it.message ?: "Unknown error"))
            }
        }
    }

    private fun reduce(
        userRepoList: List<UserRepository>? = null,
        screenState: ScreenState? = null
        ) {
        reduceState {
            it.copy(
                userRepoList = userRepoList ?: it.userRepoList,
                screenState = screenState ?: it.screenState
            )
        }
    }

    data class ViewState(
        val screenState: ScreenState = ScreenState.Loading,
        val userRepoList: List<UserRepository> = emptyList(),
        val someString: String = "Test"
    )

    companion object {
        const val USER_NAME = "octocat"
    }
}
