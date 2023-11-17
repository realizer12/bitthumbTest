package com.bithumb.test.presentation.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bithumb.test.databinding.ItemPhotoListBinding
import com.bithumb.test.presentation.model.PhotoPresentationModel
import com.bumptech.glide.Glide

class PhotoItemViewHolder(
    val binding:ItemPhotoListBinding
) :RecyclerView.ViewHolder(binding.root){
    fun bind(data:PhotoPresentationModel){
        binding.tvId.text = data.id.toString()
        binding.tvTitle.text = data.title

        Glide.with(itemView.context)
            .load(data.thumbnailUrl)
            .into(binding.tvThumbnail)
    }
}