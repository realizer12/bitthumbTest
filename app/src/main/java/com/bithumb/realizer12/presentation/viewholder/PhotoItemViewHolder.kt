package com.bithumb.realizer12.presentation.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bithumb.realizer12.databinding.ItemPhotoListBinding
import com.bithumb.realizer12.presentation.model.PhotoPresentationModel
import com.bumptech.glide.Glide

/**
 * Create Date: 2023/11/20
 *
 *
 * photo리스트에 뿌려질 item용 viewholder
 * @author LeeDongHun
 *
 **/
class PhotoItemViewHolder(
    val binding: ItemPhotoListBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: PhotoPresentationModel) {
        binding.tvId.text = data.id.toString()
        binding.tvTitle.text = data.title

        Glide.with(itemView.context)
            .load(data.thumbnailUrl)
            .into(binding.tvThumbnail)
    }
}