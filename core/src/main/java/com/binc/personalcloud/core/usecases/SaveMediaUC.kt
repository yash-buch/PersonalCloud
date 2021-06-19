package com.binc.personalcloud.core.usecases

import com.binc.personalcloud.core.BaseAsyncUseCase
import com.binc.personalcloud.core.entity.ILogger
import com.binc.personalcloud.core.entity.MediaAccessException
import com.binc.personalcloud.core.interactors.IDataRepository
import com.binc.personalcloud.core.interactors.Response
import kotlinx.coroutines.CoroutineDispatcher

class SaveMediaUC(dispatcher: CoroutineDispatcher, private val repository: IDataRepository,
                  private val logger: ILogger): BaseAsyncUseCase<Unit>(dispatcher) {

    private val TAG = SaveMediaUC::class.java.simpleName
    private lateinit var mediaPath: String
    override suspend fun doInBackground(): Response<Unit> {
        repository.savePhoto(mediaPath)
        // need to add success and failure cases
        //TODO: add cases that make sense
        return when (mediaPath) {
            "path" -> Response.Success(Unit)
            "fileNotFound" -> Response.FailedWithException(MediaAccessException("FileNotFound"))
            "" -> Response.FailedWithException(MediaAccessException("empty path"))
            else -> Response.Failure("This should not have happened")
        }
    }

    suspend fun saveMedia(path:String): Response<Unit> {
        mediaPath = path
        logger.info(TAG, mediaPath)
        //identify media type from path
        return executeAsync()
    }
}