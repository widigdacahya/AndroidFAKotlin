package com.cahyadesthian.githubuser.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cahyadesthian.githubuser.databinding.ItemGithubUserBinding
import com.cahyadesthian.githubuser.model.UserSearchResponse
import com.cahyadesthian.githubuser.model.UsersResponse
import com.cahyadesthian.githubuser.viewmodel.DetailUserViewModel

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val list = ArrayList<UserSearchResponse>()





    fun setList(users: ArrayList<UserSearchResponse>) {
        list.clear()
        list.addAll(users)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(val binding: ItemGithubUserBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(user: UserSearchResponse) {
            binding.apply {
                Glide.with(itemView)
                    .load(user.avatar_url)
                    .into(ivUserGithubPhotoItemUI)
                tvUserGithubUsernameUI.text = user.login
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemGithubUserBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}