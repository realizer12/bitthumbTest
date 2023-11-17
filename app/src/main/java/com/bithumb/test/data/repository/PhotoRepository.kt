package com.bithumb.test.data.repository

import com.bithumb.test.data.model.PhotoDataModel
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {
    suspend fun getPhoto(pageStart:Int): Flow<Result<List<PhotoDataModel>>>
}