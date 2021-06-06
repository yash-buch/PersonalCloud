package com.binc.personalcloud.core

import org.junit.Test
import com.binc.personalcloud.core.interactors.Response
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`

class BaseUseCaseTest {

    @Test
    fun test_buc_no_params() {
        val uc = TestUseCase()
        runBlocking {
            val result = uc.sampleCallNoParams()
            assertThat(result is Response.Success<String>, `is`(true))
            assertThat((result as Response.Success<String>).value, `is`("BaseUseCase no param test"))
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

    class TestUseCase: BaseUseCase<String>() {
        private lateinit var retVal: String

        suspend fun sampleCallNoParams(ret: String = "BaseUseCase no param test"): Response<String> {
            retVal = ret
            return execute()
        }

        override suspend fun doInBackground(): String {
            return retVal
        }
    }
}