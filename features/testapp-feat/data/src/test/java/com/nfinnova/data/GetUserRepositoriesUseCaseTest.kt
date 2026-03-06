package com.nfinnova.data

import com.nfinnova.data.usecases.GetUserRepositoriesUseCaseImpl
import com.nfinnova.domain.models.UserRepository
import com.nfinnova.domain.repository.GitHubRepository
import com.nfinnova.domain.usecases.GetUserRepositoriesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetUserRepositoriesUseCaseTest {
    private val repository: GitHubRepository = mockk()
    private lateinit var useCase: GetUserRepositoriesUseCase

    @Before
    fun setup() {
        useCase = GetUserRepositoriesUseCaseImpl(repository)
    }

    @Test
    fun `invoke should return list of user repositories on success`() = runTest {
        val repos = listOf(
            UserRepository("Repo1", openedIssues = 21),
            UserRepository("Repo2", openedIssues = 11)
        )

        coEvery { repository.getUserRepositories("user") } returns Result.success(repos)

        val result = useCase.invoke("user")

        assertTrue(result.isSuccess)
        assertEquals(repos, result.getOrThrow())
    }

    @Test
    fun `invoke should return failure when repository call fails`() = runTest {
        val error = Exception("Network error")
        coEvery { repository.getUserRepositories("user") } returns Result.failure(error)

        val result = useCase.invoke("user")

        assertTrue(result.isFailure)
        assertEquals(error, result.exceptionOrNull())
    }
}