package com.bithumb.realizer12.presentation.model

import android.os.Parcelable
import com.bithumb.realizer12.data.model.PhotoDataModel
import com.bithumb.realizer12.presentation.mapper.PresentationMapper
import com.bithumb.realizer12.presentation.model.PhotoPresentationModel.Companion.fromData
import kotlinx.parcelize.Parcelize


/**
 * Create Date: 2023/11/20
 *
 *
 * ui 레이어용 모델
 *
 * @see fromData
 *
 * @author LeeDongHun
 *
 **/
@Parcelize
data class PhotoPresentationModel(
    val id: Int?,
    val title: String?,
    val thumbnailUrl: String?
) : Parcelable {
    companion object : PresentationMapper<PhotoPresentationModel, PhotoDataModel> {

        /**
         * 데이터 레이어의 모델을 ui 레이어 모델로 변경
         **/
        override fun PhotoDataModel.fromData(): PhotoPresentationModel {
            return PhotoPresentationModel(
                id = id,
                title = title,
                thumbnailUrl = thumbnailUrl
            )
        }

    }
}
