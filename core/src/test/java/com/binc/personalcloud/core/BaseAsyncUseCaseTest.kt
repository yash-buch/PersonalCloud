package com.binc.personalcloud.core

import com.binc.personalcloud.core.entity.MediaAccessException
import com.binc.personalcloud.core.interactors.Response
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

@ExperimentalCoroutinesApi
class BaseAsyncUseCaseTest {
    private val testDispatcher = TestCoroutineDispatcher()

    @Test
    fun test_bauc_no_params() {
        val uc = TestUseCase(testDispatcher)
        runBlockingTest {
            val result = uc.sampleCallNoParams()
            assertThat(result is Response.Success<String>, `is`(true))
            assertThat(
                (result as Response.Success<String>).value,
                `is`("BaseUseCase no param test")
            )
        }
    }

    @Test
    fun test_bauc_params() {
        val uc = TestUseCase(testDispatcher)
        runBlocking {
            val result = uc.sampleCallNoParams("BaseUseCase test")
            assertThat(result is Response.Success<String>, `is`(true))
            assertThat(
                (result as Response.Success<String>).value,
                `is`("BaseUseCase test")
            )
        }
    }

    @Test
    fun test_bauc_no_params_fail() {
        val uc = TestUseCaseTwo(testDispatcher)
        runBlocking {
            val result = uc.sampleCallNoParams("BaseUseCase test")
            assertThat(result is Response.Failure<String>, `is`(true))
            assertThat(
                (result as Response.Failure<String>).error is MediaAccessException,
                `is`(true)
            )
            assertThat(
                result.error.message,
                `is`("Something went wrong")
            )
        }
    }

    class TestUseCase constructor(dispatcher: TestCoroutineDispatcher) :
        BaseAsyncUseCase<String>(dispatcher) {
        lateinit var retVal: String

        suspend fun sampleCallNoParams(ret: String = "BaseUseCase no param test"): Response<String> {
            retVal = ret
            return executeAsync()
        }

        override suspend fun doInBackground(): Response<String> {
            return Response.Success(retVal)
        }
    }

    class TestUseCaseTwo constructor(dispatcher: TestCoroutineDispatcher) :
        BaseAsyncUseCase<String>(dispatcher) {
        lateinit var retVal: String

        suspend fun sampleCallNoParams(ret: String = "BaseUseCase no param test"): Response<String> {
            retVal = ret
            return executeAsync()
        }

        override suspend fun doInBackground(): Response<String> {
            return Response.Failure(MediaAccessException("Something went wrong"))
        }
    }
}