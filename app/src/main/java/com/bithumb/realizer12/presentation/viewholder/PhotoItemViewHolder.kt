package com.bithumb.realizer12.presentation.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bithumb.realizer12.databinding.ItemPhotoListBinding
import com.bithumb.realizer12.presentation.model.PhotoPresentationModel
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