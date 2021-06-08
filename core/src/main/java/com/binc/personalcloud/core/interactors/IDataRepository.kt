package com.binc.personalcloud.core.interactors

import com.binc.personalcloud.core.entity.IMedia

interface IDataRepository {
    fun getData(): List<IMedia>? = null
    fun getPhotos(): List<IMedia>? = null
    fun getVideos(): List<IMedia>? = null
}