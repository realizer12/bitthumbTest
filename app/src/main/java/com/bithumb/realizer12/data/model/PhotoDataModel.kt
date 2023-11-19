package com.bithumb.realizer12.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhotoDataModel(
    val albumId:Int?=null,
    val id:Int?=null,
    val title:String?=null,
    var url:String?=null,
    val thumbnailUrl:String?=null
):Parcelable
