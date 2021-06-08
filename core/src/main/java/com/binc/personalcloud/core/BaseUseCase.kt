package com.binc.personalcloud.core

import com.binc.personalcloud.core.interactors.Response

abstract class BaseUseCase<O> {
    protected abstract suspend fun doInForeground(): Response<O>

    protected suspend fun execute(): Response<O> = doInForeground()
}