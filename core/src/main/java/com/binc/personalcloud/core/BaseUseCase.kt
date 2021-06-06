package com.binc.personalcloud.core

import com.binc.personalcloud.core.interactors.Response

abstract class BaseUseCase<O> {
    protected abstract suspend fun doInBackground(): O

    protected suspend fun execute(): Response<O> = Response.Success(doInBackground())
}