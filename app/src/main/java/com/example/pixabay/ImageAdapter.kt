package com.example.pixabay

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pixabay.databinding.ItemImageBinding
import com.example.pixabay.network.ImageModel
import com.example.pixabay.network.PixabayModel
import java.util.ArrayList

class ImageAdapter() : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    private var array = arrayListOf<ImageModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.onBind(array[position])
    }

    override fun getItemCount(): Int = array.size
    fun array(arrayList: ArrayList<ImageModel>) {
        array = arrayList
    }

    class ImageViewHolder(private val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: ImageModel) {
            binding.imageView.load(model.previewURL)
        }
    }
}
