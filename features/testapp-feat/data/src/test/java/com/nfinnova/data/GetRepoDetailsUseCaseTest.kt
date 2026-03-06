package com.nfinnova.data

import com.nfinnova.data.usecases.GetRepoDetailsUseCaseImpl
import com.nfinnova.domain.models.RepoDetailsHeaderData
import com.nfinnova.domain.models.TagData
import com.nfinnova.domain.repository.GitHubRepository
import com.nfinnova.domain.usecases.GetRepoDetailsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetRepoDetailsUseCaseTest {

    private val repository: GitHubRepository = mockk()
    private lateinit var useCase: GetRepoDetailsUseCase

    @Before
    fun setup() {
        useCase = GetRepoDetailsUseCaseImpl(repository)
    }

    @Test
    fun `invoke should return combined RepoDetailsData when both calls succeed`() = runTest {
        val header = RepoDetailsHeaderData(
            userAvatarUrl = "https://avatars.githubusercontent.com/u/12345",
            userName = "Fedja",
            repoName = "MyRepo",
            numberOfForks = 10,
            numberOfWatchers = 42
        )

        val tags = listOf(
            TagData(commitName = "v1.0", commitSha = "abc123"),
            TagData(commitName = "v1.1", commitSha = "def456")
        )

        coEvery { repository.getRepoHeaderData("user", "repo") } returns Result.success(header)
        coEvery { repository.getRepoTags("user", "repo") } returns Result.success(tags)

        val result = useCase.invoke("user", "repo")

        assertTrue(result.isSuccess)
        val data = result.getOrThrow()
        assertEquals(header, data.repoDetailsHeaderData)
        assertEquals(tags, data.repoTags)
    }

    @Test
    fun `invoke should return empty tags when tags call fails`() = runTest {
        val header = RepoDetailsHeaderData(
            userAvatarUrl = "https://avatars.githubusercontent.com/u/4426146?v=4",
            userName = "Fedja",
            repoName = "MyRepo",
            numberOfForks = 10,
            numberOfWatchers = 42
        )

        coEvery { repository.getRepoHeaderData("user", "repo") } returns Result.success(header)
        coEvery { repository.getRepoTags("user", "repo") } returns Result.failure(Exception("Tags error"))

        val result = useCase.invoke("user", "repo")

        assertTrue(result.isSuccess)
        val data = result.getOrThrow()
        assertEquals(header, data.repoDetailsHeaderData)
        assertTrue(data.repoTags.isEmpty())
    }

    @Test
    fun `invoke should return failure when header call fails`() = runTest {
        val error = Exception("Header error")
        coEvery { repository.getRepoHeaderData("user", "repo") } returns Result.failure(error)

        val result = useCase.invoke("user", "repo")

        assertTrue(result.isFailure)
    }
}
