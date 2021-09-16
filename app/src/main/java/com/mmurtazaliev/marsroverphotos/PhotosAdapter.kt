package com.mmurtazaliev.marsroverphotos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mmurtazaliev.marsroverphotos.api.NasaApi
import javax.inject.Inject


class PhotosAdapter(screenWidth: Int) : RecyclerView.Adapter<PhotosViewHolder>() {

    private var photos = listOf<NasaApi.Photo>()
    private var sizeImageView = 0

    init {
        sizeImageView = screenWidth / 3
    }

    fun setItems(items: List<NasaApi.Photo>) {
        photos = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return PhotosViewHolder(view, sizeImageView)
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    override fun getItemCount(): Int = photos.size
}

class PhotosViewHolder(view: View, sizeImageView: Int) : RecyclerView.ViewHolder(view) {

    private val photoIV: ImageView = view.findViewById(R.id.photoIV)

    init {
        photoIV.setOnClickListener { }
        photoIV.layoutParams = FrameLayout.LayoutParams(sizeImageView, sizeImageView)
    }

    fun bind(item: NasaApi.Photo) {
        Glide
            .with(photoIV)
            .load(item.imgSrc)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.color.black)
            .into(photoIV)
    }
}