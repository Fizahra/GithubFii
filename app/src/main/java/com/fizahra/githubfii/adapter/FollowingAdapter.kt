package com.fizahra.githubfii.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fizahra.githubfii.databinding.ItemUserBinding
import com.fizahra.githubfii.model.FollowResponse

class FollowingAdapter : RecyclerView.Adapter<FollowingAdapter.FollowingViewHolder>() {

    private val listFollowing = ArrayList<FollowResponse.FollowResponseItem>()

    fun setList(followings : ArrayList<FollowResponse.FollowResponseItem>){
        listFollowing.clear()
        listFollowing.addAll(followings)
        notifyDataSetChanged()
    }

    class FollowingViewHolder(val binding : ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(following : FollowResponse.FollowResponseItem){
            binding.apply {
                tvUser.text = following.login
                Glide.with(itemView)
                    .load(following.avatarUrl)
                    .into(imgUser)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingAdapter.FollowingViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowingAdapter.FollowingViewHolder(view)
    }

    override fun getItemCount(): Int = listFollowing.size

    override fun onBindViewHolder(holder: FollowingAdapter.FollowingViewHolder, position: Int) {
        holder.bind(listFollowing[position])
    }
}