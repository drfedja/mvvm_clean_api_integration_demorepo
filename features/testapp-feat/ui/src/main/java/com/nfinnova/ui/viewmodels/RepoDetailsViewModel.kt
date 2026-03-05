package com.nfinnova.ui.viewmodels

import com.nfinnova.core_ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RepoDetailsViewModel @Inject constructor(): BaseViewModel<RepoDetailsViewModel.ViewState>() {
    override fun getInitialState(): ViewState = ViewState(
        message = "Next screen"
    )

    data class ViewState(
        val message: String
    )
}
