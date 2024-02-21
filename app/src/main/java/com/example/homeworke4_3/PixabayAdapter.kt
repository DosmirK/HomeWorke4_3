package com.example.homeworke4_3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import coil.load
import com.example.homeworke4_3.databinding.ItemImageBinding

class PixabayAdapter(val list: ArrayList<ImageModel>): Adapter<PixabayAdapter.PixabayViewHolder>() {
    class PixabayViewHolder(var binding: ItemImageBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(imageModel: ImageModel){
            binding.tvLike.text = imageModel.likes.toString()
            binding.ivPixaImage.load(imageModel.largeImageURL)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PixabayViewHolder {
        return PixabayViewHolder(
            ItemImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: PixabayViewHolder, position: Int) {
        holder.onBind(list[position])
    }
}