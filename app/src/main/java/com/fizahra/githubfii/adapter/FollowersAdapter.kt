package com.fizahra.githubfii.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fizahra.githubfii.databinding.ItemUserBinding
import com.fizahra.githubfii.model.FollowResponse

class FollowersAdapter : RecyclerView.Adapter<FollowersAdapter.FollowerViewHolder>() {

    private val listFollower = ArrayList<FollowResponse.FollowResponseItem>()

    fun setList(followers : ArrayList<FollowResponse.FollowResponseItem>){
        listFollower.clear()
        listFollower.addAll(followers)
        notifyDataSetChanged()
    }

    class FollowerViewHolder(val binding : ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(follower : FollowResponse.FollowResponseItem){
            binding.apply {
                tvUser.text = follower.login
                Glide.with(itemView)
                    .load(follower.avatarUrl)
                    .into(imgUser)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowersAdapter.FollowerViewHolder(view)
    }

    override fun getItemCount(): Int = listFollower.size

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.bind(listFollower[position])
    }
}