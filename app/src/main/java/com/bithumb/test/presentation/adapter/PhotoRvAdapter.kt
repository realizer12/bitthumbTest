package com.bithumb.test.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bithumb.test.R
import com.bithumb.test.databinding.ItemPhotoListBinding
import com.bithumb.test.presentation.model.PhotoPresentationModel
import com.bithumb.test.presentation.viewholder.PhotoItemViewHolder


class PhotoRvAdapter:ListAdapter<PhotoPresentationModel,ViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemPhotoListBinding :ItemPhotoListBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_photo_list,
                parent,
                false
            )
        return PhotoItemViewHolder(itemPhotoListBinding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as PhotoItemViewHolder).apply {
            bind(currentList[bindingAdapterPosition])
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<PhotoPresentationModel>() {
            override fun areItemsTheSame(
                oldItem: PhotoPresentationModel,
                newItem: PhotoPresentationModel
            ): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(
                oldItem: PhotoPresentationModel,
                newItem: PhotoPresentationModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}