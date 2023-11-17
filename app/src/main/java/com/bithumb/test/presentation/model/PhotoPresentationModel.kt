package com.bithumb.test.presentation.model

import android.os.Parcelable
import com.bithumb.test.data.model.PhotoDataModel
import com.bithumb.test.presentation.mapper.PresentationMapper
import kotlinx.parcelize.Parcelize


/**
 * Date: 2023/11/14
 *
 *
 * Author: idonghun
 * @param id photo id
 * @param title
 * **/
@Parcelize
data class PhotoPresentationModel(
    val id: Int?,
    val title: String?,
    val thumbnailUrl: String?
) : Parcelable {
    companion object : PresentationMapper<PhotoPresentationModel, PhotoDataModel> {
        override fun PhotoDataModel.fromData(): PhotoPresentationModel {
            return PhotoPresentationModel(
                id = id,
                title = title,
                thumbnailUrl = thumbnailUrl
            )
        }

    }
}
