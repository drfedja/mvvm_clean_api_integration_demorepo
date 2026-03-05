package com.nfinnova.ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.nfinnova.core_ui.BaseViewModel
import com.nfinnova.core_ui.screen_state.ScreenState
import com.nfinnova.domain.models.RepoDetailsHeaderData
import com.nfinnova.domain.models.TagData
import com.nfinnova.domain.usecases.GetRepoDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class RepoDetailsViewModel @Inject constructor(
    val getRepoDetailsUseCase: GetRepoDetailsUseCase
): BaseViewModel<RepoDetailsViewModel.ViewState>() {
    override fun getInitialState(): ViewState = ViewState(
        loadUserRepoData = ::loadUserRepoData
    )

    private fun loadUserRepoData(user: String, repo: String) {
        viewModelScope.launch {
            getRepoDetailsUseCase.invoke(user, repo)
                .onSuccess {
                    reduce(
                        screenState = ScreenState.Success,
                        repoDetailsHeaderData = it.repoDetailsHeaderData,
                        repoTags = it.repoTags
                    )
                }.onFailure {
                    reduce(screenState = ScreenState.Failure(it.message ?: "Unknown error"))
                }
        }
    }

    private fun reduce(
        repoDetailsHeaderData: RepoDetailsHeaderData? = null,
        repoTags: List<TagData>? = null,
        screenState: ScreenState? = null
    ) {
        reduceState {
            it.copy(
                repoDetailsHeaderData = repoDetailsHeaderData ?: it.repoDetailsHeaderData,
                repoTags = repoTags ?: it.repoTags,
                screenState = screenState ?: it.screenState
            )
        }
    }

    data class ViewState(
        val screenState: ScreenState = ScreenState.Loading,
        val repoDetailsHeaderData: RepoDetailsHeaderData = RepoDetailsHeaderData.EMPTY,
        val repoTags: List<TagData> = listOf(),
        val loadUserRepoData: (user: String, repo: String) -> Unit = { _, _ -> }
    )
}
