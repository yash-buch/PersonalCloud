package com.binc.personalcloud.core.interactors

import org.junit.Test
import org.mockito.Mockito

class IDataRepositoryTest {
    open class Repository: IDataRepository
    private val mockRepo: Repository = Mockito.mock(Repository::class.java)

    @Test
    fun getData_exists_test() {
        mockRepo.getData()
        Mockito.verify(mockRepo).getData()
    }

    @Test
    fun getPhotos_exists_test() {
        mockRepo.getPhotos()
        Mockito.verify(mockRepo).getPhotos()
    }

    @Test
    fun getVideos_exists_test() {
        mockRepo.getVideos()
        Mockito.verify(mockRepo).getVideos()
    }
}