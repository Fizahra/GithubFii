package com.fizahra.githubfii.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fizahra.githubfii.DetailUserActivity
import com.fizahra.githubfii.databinding.ItemUserBinding
import com.fizahra.githubfii.model.UserResponse

class UserAdapter(private val listUser : ArrayList<UserResponse.User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    fun setListUser(users : ArrayList<UserResponse.User>){
        listUser.clear()
        listUser.addAll(users)
        notifyDataSetChanged()
    }

    class UserViewHolder(val binding : ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user : UserResponse.User){
            binding.apply {
                Glide.with(itemView)
                    .load(user.avatarUrl)
                    .into(imgUser)
                tvUser.text = user.login
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int = listUser.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(listUser[position])
        holder.itemView.setOnClickListener {
            val detailIntent = Intent(holder.itemView.context, DetailUserActivity::class.java)
            detailIntent.putExtra("username", listUser[holder.adapterPosition])
            holder.itemView.context.startActivity(detailIntent)
        }
    }
}