package com.binc.personalcloud.core

import com.binc.personalcloud.core.interactors.Response
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Assert.*
import org.junit.Test

@ExperimentalCoroutinesApi
class BaseAsyncUseCaseTest {
    private val testDispatcher = TestCoroutineDispatcher()

    @Test
    fun test_buc_no_params() {
        val uc = TestUseCase(testDispatcher)
        runBlockingTest {
            val result = uc.sampleCallNoParams()
            MatcherAssert.assertThat(result is Response.Success<String>, Matchers.`is`(true))
            MatcherAssert.assertThat(
                (result as Response.Success<String>).value,
                Matchers.`is`("BaseUseCase no param test")
            )
        }
    }

    @Test
    fun test_buc_params() {
        val uc = TestUseCase(testDispatcher)
        runBlocking {
            val result = uc.sampleCallNoParams("BaseUseCase test")
            MatcherAssert.assertThat(result is Response.Success<String>, Matchers.`is`(true))
            MatcherAssert.assertThat(
                (result as Response.Success<String>).value,
                Matchers.`is`("BaseUseCase test")
            )
        }
    }

    class TestUseCase constructor(dispatcher: TestCoroutineDispatcher):
        BaseAsyncUseCase<String>(dispatcher) {
        lateinit var retVal: String

        suspend fun sampleCallNoParams(ret: String = "BaseUseCase no param test"): Response<String> {
            retVal = ret
            return executeAsync()
        }

        override suspend fun doInBackground(): String {
            return retVal
        }
    }
}