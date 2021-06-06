package com.binc.personalcloud.core

import com.binc.personalcloud.core.interactors.Response
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class BaseAsyncUseCase<O>(private val dispatcher: CoroutineDispatcher) {
    protected abstract suspend fun doInBackground(): O

    protected suspend fun executeAsync(): Response<O> = withContext(dispatcher) {
        return@withContext Response.Success(doInBackground())
    }
}