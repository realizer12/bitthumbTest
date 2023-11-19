package com.bithumb.realizer12.data.repository

import com.bithumb.realizer12.data.model.PhotoDataModel
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {
    suspend fun getPhoto(pageStart:Int): Flow<Result<List<PhotoDataModel>>>
}