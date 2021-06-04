package com.binc.personalcloud.core

import com.binc.personalcloud.core.interactors.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseAsyncUseCase<O>(private val dispatcher: CoroutineDispatcher) {
    protected abstract suspend fun doInBackground(): O

    protected suspend fun executeAsync(): Result<O> = withContext(dispatcher) {
        return@withContext Result(doInBackground())
    }
}