package com.fizahra.githubfii.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fizahra.githubfii.DetailUserActivity
import com.fizahra.githubfii.database.FavUser
import com.fizahra.githubfii.databinding.ItemUserBinding

class FavUserAdapter : RecyclerView.Adapter<FavUserAdapter.FavUserViewHolder>() {
    private val favUserList = ArrayList<FavUser>()

    fun setList(favUsers : ArrayList<FavUser>){
        favUserList.clear()
        favUsers.addAll(favUsers)
        notifyDataSetChanged()
    }
    class FavUserViewHolder(val binding : ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(favUser : FavUser){
            binding.apply {
                tvUser.text = favUser.username
                Glide.with(itemView)
                    .load(favUser.avatar_url)
                    .into(imgUser)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavUserViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavUserViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavUserViewHolder, position: Int) {
        holder.bind(favUserList[position])
        holder.itemView.setOnClickListener {
            val detailIntent = Intent(holder.itemView.context, DetailUserActivity::class.java)
            detailIntent.putExtra("username", favUserList[holder.adapterPosition])
            holder.itemView.context.startActivity(detailIntent)
        }
    }

    override fun getItemCount(): Int = favUserList.size
}