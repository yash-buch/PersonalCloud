package com.binc.personalcloud.core.interactors

import com.binc.personalcloud.core.entity.PlaceHolder

interface DataRepository {
    fun getData(): List<PlaceHolder>
    fun getPhotos(): List<PlaceHolder>
    fun getVideos(): List<PlaceHolder>
}