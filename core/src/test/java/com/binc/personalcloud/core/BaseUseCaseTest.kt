package com.binc.personalcloud.core

import com.binc.personalcloud.core.entity.MediaAccessException
import com.binc.personalcloud.core.interactors.Response
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class BaseUseCaseTest {

    @Test
    fun test_buc_no_params() {
        val uc = TestUseCase()
        runBlocking {
            val result = uc.sampleCallNoParams()
            assertThat(result is Response.Success<String>, `is`(true))
            assertThat(
                (result as Response.Success<String>).value,
                `is`("BaseUseCase no param test")
            )
        }
    }

    @Test
    fun test_buc_params() {
        val uc = TestUseCase()
        runBlocking {
            val result = uc.sampleCallNoParams("BaseUseCase test")
            assertThat(result is Response.Success<String>, `is`(true))
            assertThat((result as Response.Success<String>).value, `is`("BaseUseCase test"))
        }
    }

    @Test
    fun test_buc_no_params_fail() {
        val uc = TestUseCaseTwo()
        runBlocking {
            val result = uc.sampleCallNoParams("BaseUseCase test")
            assertThat(result is Response.FailedWithException<String>, `is`(true))
            assertThat(
                (result as Response.FailedWithException<String>).error is MediaAccessException,
                `is`(true)
            )
            assertThat(
                result.error.message,
                `is`("Something went wrong")
            )
        }
    }

    class TestUseCase : BaseUseCase<String>() {
        private lateinit var retVal: String

        suspend fun sampleCallNoParams(ret: String = "BaseUseCase no param test"): Response<String> {
            retVal = ret
            return execute()
        }

        override suspend fun doInForeground(): Response<String> {
            return Response.Success(retVal)
        }
    }

    class TestUseCaseTwo : BaseUseCase<String>() {
        private lateinit var retVal: String

        suspend fun sampleCallNoParams(ret: String = "BaseUseCase no param test"): Response<String> {
            retVal = ret
            return execute()
        }

        override suspend fun doInForeground(): Response<String> {
            return Response.FailedWithException(MediaAccessException("Something went wrong"))
        }
    }
}