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

class UserAdapterComplete : RecyclerView.Adapter<UserAdapterComplete.UserViewHolder>() {

    private val listUser = ArrayList<UsersResponse>()


    fun setListComplete(users: ArrayList<UsersResponse>) {
        listUser.clear()
        listUser.addAll(users)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(val binding: ItemGithubUserBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(user: UsersResponse) {
            binding.apply {
                //Glide.with(itemView)
                tvUserGithubUsernameUI.text = user.login
                tvUserGithubNameUI.text = user.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemGithubUserBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(listUser[position])
    }

    override fun getItemCount(): Int = listUser.size
}