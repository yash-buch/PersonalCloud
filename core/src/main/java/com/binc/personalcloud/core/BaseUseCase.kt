package com.binc.personalcloud.core

import com.binc.personalcloud.core.interactors.Result

abstract class BaseUseCase<O> {
    protected abstract suspend fun doInBackground(): O

    protected suspend fun execute(): Result<O> = Result(doInBackground())
}