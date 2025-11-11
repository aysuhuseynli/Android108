package com.example.android108

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android108.databinding.UserViewBinding
import com.example.android108.db.UserEntity

class UserAdapter(
    private val userList: MutableList<UserEntity>
): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewHolder {
        val binding = UserViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: UserViewHolder,
        position: Int
    ) {
        val user = userList[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class UserViewHolder(private val binding: UserViewBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(user: UserEntity){
            binding.username.text = user.username
        }
    }

    fun removeAt(position: Int){
        userList.removeAt(position)
        notifyItemRemoved(position)
    }
}