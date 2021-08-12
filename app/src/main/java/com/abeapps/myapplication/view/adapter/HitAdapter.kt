package com.abeapps.myapplication.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abeapps.myapplication.R
import com.abeapps.myapplication.databinding.HitItemLayoutBinding
import com.abeapps.myapplication.model.data.Hit
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class HitAdapter : RecyclerView.Adapter<HitViewHolder>() {

    private val hitList: MutableList<Hit> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HitViewHolder {
        val binding = HitItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HitViewHolder(binding)
    }

    fun addAllHits(list: List<Hit>){
        hitList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: HitViewHolder, position: Int) {
        hitList[position].let { hit ->
            holder.binding.apply {
                likesTv.text = hit.likes.toLikes()
                userTv.text = hit.user

                Glide
                    .with(this.root)
                    .applyDefaultRequestOptions(RequestOptions().centerCrop())
                    .load(hit.largeImageURL)
                    .placeholder(R.drawable.placeholder_camera)
                    .into(pictureIv)

                Glide
                    .with(this.root)
                    .applyDefaultRequestOptions(RequestOptions().circleCrop())
                    .load(hit.userImageURL)
                    .placeholder(R.drawable.user_placeholder)
                    .into(profileIv)
            }
        }
    }

    override fun getItemCount(): Int {
        return hitList.size
    }


}

private fun Int.toLikes(): String {
    return when {
        this > 1 -> "$this likes"
        this <= 0 -> "$this like"
        else -> "no likes"
    }
}