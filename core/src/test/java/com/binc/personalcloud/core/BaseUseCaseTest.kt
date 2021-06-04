package com.binc.personalcloud.core

import org.junit.Test
import com.binc.personalcloud.core.interactors.Result
import kotlinx.coroutines.runBlocking
import org.junit.Assert

class BaseUseCaseTest {

    @Test
    fun test_buc_no_params() {
        val uc = TestUseCase()
        runBlocking {
            val result = uc.sampleCallNoParams()
            Assert.assertEquals(result.result, "BaseUseCase no param test")
        }
    }

    @Test
    fun test_buc_params() {
        val uc = TestUseCase()
        runBlocking {
            val result = uc.sampleCallNoParams("BaseUseCase test")
            Assert.assertEquals(result.result, "BaseUseCase test")
        }
    }

    class TestUseCase: BaseUseCase<String>() {
        lateinit var retVal: String

        suspend fun sampleCallNoParams(ret: String = "BaseUseCase no param test"): Result<String> {
            retVal = ret
            return execute()
        }

        override suspend fun doInBackground(): String {
            return retVal
        }
    }
}