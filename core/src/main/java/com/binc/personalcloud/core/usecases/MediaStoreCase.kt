package com.binc.personalcloud.core.usecases

import com.binc.personalcloud.core.BaseAsyncUseCase
import com.binc.personalcloud.core.entity.ILogger
import com.binc.personalcloud.core.entity.IMedia
import com.binc.personalcloud.core.entity.MediaAccessException
import com.binc.personalcloud.core.interactors.IDataRepository
import com.binc.personalcloud.core.interactors.Response
import kotlinx.coroutines.CoroutineDispatcher

class MediaStoreCase(dispatcher: CoroutineDispatcher, private val repository: IDataRepository,
                     private val logger: ILogger)
    : BaseAsyncUseCase<List<IMedia>>(dispatcher) {

    lateinit var mediaType: MediaType

    suspend fun getPhotos(): Response<List<IMedia>> {
        mediaType = MediaType.PHOTO
        return executeAsync()
    }

    suspend fun getVideos(): Response<List<IMedia>> {
        mediaType = MediaType.VIDEO
        return executeAsync()
    }

    suspend fun getAllMedia(): Response<List<IMedia>> {
        mediaType = MediaType.ALL
        return executeAsync()
    }

    override suspend fun doInBackground(): Response<List<IMedia>> {
        val result = when(mediaType) {
            MediaType.PHOTO -> repository.getPhotos()
            MediaType.VIDEO -> repository.getVideos()
            MediaType.ALL -> repository.getData()
        }
        return result?.let { Response.Success(it) }
            ?: Response.Failure(MediaAccessException("Error in fetching media"))
    }
}

sealed class MediaType {
    object PHOTO : MediaType()
    object VIDEO : MediaType()
    object ALL: MediaType()
}