package com.nfinnova.ui

import com.nfinnova.core_ui.screen_state.ScreenState
import com.nfinnova.domain.models.UserRepository
import com.nfinnova.domain.usecases.GetUserRepositoriesUseCase
import com.nfinnova.ui.viewmodels.UserReposViewModel
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

@ExperimentalCoroutinesApi
class UserReposViewModelTest {

    private val getUserRepositoriesUseCase: GetUserRepositoriesUseCase = mockk()
    private lateinit var viewModel: UserReposViewModel

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
    fun initShouldLoadUserRepositoriesSuccessfully() = runTest {
        val repos = listOf(
            UserRepository("Repo1", openedIssues = 22),
            UserRepository("Repo2", openedIssues = 14)
        )

        coEvery { getUserRepositoriesUseCase.invoke(UserReposViewModel.USER_NAME) } returns Result.success(repos)

        viewModel = UserReposViewModel(getUserRepositoriesUseCase)

        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.state.value
        assertEquals(ScreenState.Success, state.screenState)
        assertEquals(repos, state.userRepoList)
        assertEquals(UserReposViewModel.USER_NAME, state.repoUserName)
    }

    @Test
    fun initShouldHandleFailure() = runTest {
        val error = Exception("Network error")

        coEvery { getUserRepositoriesUseCase.invoke(UserReposViewModel.USER_NAME) } returns Result.failure(error)

        viewModel = UserReposViewModel(getUserRepositoriesUseCase)
        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.state.value
        assertTrue(state.screenState is ScreenState.Failure)
        assertEquals(error.message.orEmpty(), (state.screenState as ScreenState.Failure).message)
    }
}
