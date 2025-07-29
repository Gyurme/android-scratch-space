package com.example.marsphotos.fake

import com.example.marsphotos.data.NetworkMarsPhotosRepository
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class NetworkMarsRepositoryTest {

    @Test
    fun networkMarsRepository_getMarsPhotos_verifyPhotoList() {
        runTest{
            val repository = NetworkMarsPhotosRepository(
                marsApiService = FakeMarsApiService()
            )
            assertEquals(FakeDataSource.photosList, repository.getMarsPhotos())
        }
    }
}