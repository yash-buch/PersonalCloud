package com.binc.personalcloud.core.usecases

import com.binc.personalcloud.core.interactors.IDataRepository
import com.binc.personalcloud.core.interactors.Response
import com.binc.personalcloud.core.util.ConsoleLogger
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Before

import org.junit.Test
import org.mockito.Mockito

@ExperimentalCoroutinesApi //for TestCoroutineDispatcher
class SaveMediaUCTest {
    open class Repository: IDataRepository
    private lateinit var useCase: SaveMediaUC
    private lateinit var testDispatcher: TestCoroutineDispatcher
    private lateinit var mockRepo: Repository

    @Before
    fun setup() {
        testDispatcher = TestCoroutineDispatcher()
        mockRepo = Mockito.mock(Repository::class.java)
        useCase = SaveMediaUC(testDispatcher, mockRepo, ConsoleLogger())
    }

    @Test
    fun test_doInBackground() {
        runBlockingTest {
            val response: Response<Unit> = useCase.saveMedia("path")
            assertThat(response is Response.Success<Unit>, `is`(true))
            Mockito.verify(mockRepo).savePhoto("path")
        }
    }

    @Test
    fun test_saveMedia() {
        runBlockingTest {
            val response: Response<Unit> = useCase.saveMedia("path")
            assertThat(response is Response.Success<Unit>, `is`(true))
        }
    }

    @Test
    fun test_saveMedia_empty_path() {
        runBlockingTest {
            val response: Response<Unit> = useCase.saveMedia("")
            assertThat(response is Response.FailedWithException, `is`(true))
            assertThat((response as Response.FailedWithException).error.message,
                `is`("empty path"))
        }
    }

    @Test
    fun test_saveMedia_file_not_found() {
        runBlockingTest {
            val response: Response<Unit> = useCase.saveMedia("fileNotFound")
            assertThat(response is Response.FailedWithException, `is`(true))
            assertThat((response as Response.FailedWithException).error.message,
                `is`("FileNotFound"))
        }
    }
}