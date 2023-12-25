package com.bithumb.realizer12.data.datasource.remote

import com.bithumb.realizer12.data.model.PhotoDataModel
import kotlinx.coroutines.flow.Flow

/**
 * Create Date: 2023/11/20
 *
 *
 * photo관련 Remote 데이터소스 기능이 정의된 interface
 * @author LeeDongHun
 *
 *
 **/
interface PhotoRemoteDataSource {
    suspend fun getPhotoList(pageStart:Int,pageLimit:Int): Flow<Result<List<PhotoDataModel>>>
}