package com.bithumb.realizer12.presentation.model

import android.os.Parcelable
import com.bithumb.realizer12.data.model.PhotoDataModel
import com.bithumb.realizer12.presentation.mapper.PresentationMapper
import kotlinx.parcelize.Parcelize


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
