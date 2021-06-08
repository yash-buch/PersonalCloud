package com.binc.personalcloud.core.interactors

sealed class Response<O> {
    class Success<O> (val value: O): Response<O>()
    class Failure<O> (val error: Throwable): Response<O>()
}