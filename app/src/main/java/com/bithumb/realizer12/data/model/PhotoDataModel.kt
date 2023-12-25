package com.bithumb.realizer12.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Create Date: 2023/11/20
 *
 * 데이터 레이어에서 사용되는 모델
 * 서버에서 주는 response 형태와 동일하게 구성된다.
 *
 * @author LeeDongHun
 *
 **/
@Parcelize
data class PhotoDataModel(
    val albumId:Int?=null,
    val id:Int?=null,
    val title:String?=null,
    var url:String?=null,
    val thumbnailUrl:String?=null
):Parcelable
