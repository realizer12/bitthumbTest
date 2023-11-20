package com.bithumb.realizer12.data.repository.impl

import com.bithumb.realizer12.data.datasource.remote.PhotoRemoteDataSource
import com.bithumb.realizer12.data.model.PhotoDataModel
import com.bithumb.realizer12.data.repository.PhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class PhotoRepositoryImpl @Inject constructor(
    private val photoRemoteDataSource: PhotoRemoteDataSource
) : PhotoRepository {

    //무조건 한개만 가져올때 사용 .
    override suspend fun getPhoto(pageStart: Int): Flow<Result<List<PhotoDataModel>>> {
        return photoRemoteDataSource.getPhotoList(pageStart = pageStart, pageLimit = 1)
            .flowOn(Dispatchers.IO)
    }
}