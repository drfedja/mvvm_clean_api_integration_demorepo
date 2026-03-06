package com.nfinnova.ui

import com.nfinnova.core_ui.screen_state.ScreenState
import com.nfinnova.domain.models.RepoDetailsData
import com.nfinnova.domain.models.RepoDetailsHeaderData
import com.nfinnova.domain.models.TagData
import com.nfinnova.domain.usecases.GetRepoDetailsUseCase
import com.nfinnova.ui.viewmodels.RepoDetailsViewModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RepoDetailsViewModelTest {
    private val getRepoDetailsUseCase: GetRepoDetailsUseCase = mockk()
    private lateinit var viewModel: RepoDetailsViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun loadUserRepoDataShouldUpdateStateOnSuccess() = runTest {
        val header = RepoDetailsHeaderData(
            userAvatarUrl = "avatar",
            userName = "user",
            repoName = "repo",
            numberOfForks = 5,
            numberOfWatchers = 10
        )
        val tags = listOf(TagData("v1.0", "abc123"))

        coEvery { getRepoDetailsUseCase.invoke("user", "repo") } returns Result.success(
            RepoDetailsData(header, tags)
        )

        viewModel = RepoDetailsViewModel(getRepoDetailsUseCase)
        viewModel.state.value.loadUserRepoData("user", "repo")
        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.state.value
        assertEquals(ScreenState.Success, state.screenState)
        assertEquals(header, state.repoDetailsHeaderData)
        assertEquals(tags, state.repoTags)
    }

    @Test
    fun loadUserRepoDataShouldHandleFailure() = runTest {
        val error = Exception("Network error")
        coEvery { getRepoDetailsUseCase.invoke("user", "repo") } returns Result.failure(error)

        viewModel = RepoDetailsViewModel(getRepoDetailsUseCase)
        viewModel.state.value.loadUserRepoData("user", "repo")
        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.state.value
        assertTrue(state.screenState is ScreenState.Failure)
        assertEquals(error.message.orEmpty(), (state.screenState as ScreenState.Failure).message)
    }
}