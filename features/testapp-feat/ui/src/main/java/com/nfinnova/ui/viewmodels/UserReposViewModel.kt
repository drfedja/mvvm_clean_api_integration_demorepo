package com.nfinnova.ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.nfinnova.core_ui.BaseViewModel
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
            getUserRepositoriesUseCase.invoke("drfedja").onSuccess {
                it.forEach { userRepository ->
                    println("repo name: ${userRepository.repositoryName}")
                }
            }
        }
    }

    data class ViewState(
        val userRepoList: List<String> = emptyList(),
        val someString: String = "Test"
    )
}
