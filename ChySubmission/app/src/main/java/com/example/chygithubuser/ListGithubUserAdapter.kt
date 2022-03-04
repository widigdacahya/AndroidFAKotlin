package com.example.chygithubuser

import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chygithubuser.databinding.ItemGithubUserBinding

class ListGithubUserAdapter(private val listOfGithubUser: ArrayList<GithubUser>) : RecyclerView.Adapter<ListGithubUserAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(var bindingListItem : ItemGithubUserBinding) : RecyclerView.ViewHolder(bindingListItem.root) {

    }


    interface OnItemClickCallback {
        fun onItemClicked(userData: GithubUser)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemGithubBinding = ItemGithubUserBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ListViewHolder(itemGithubBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (userUsername, userName, userLocation, userRepo, userCompany, userFollower, userFollowing, userAvatar) = listOfGithubUser[position]
        holder.bindingListItem.tvUserGithubNameUI.text = userName
        holder.bindingListItem.tvUserGithubUsernameUI.text = userUsername
        holder.bindingListItem.tvRepoUserGithubUI.text = userRepo
        holder.bindingListItem.tvCompanyUserGithubUI.text = userCompany
        holder.bindingListItem.ivUserGithubPhotoUI.setImageResource(userAvatar)

        holder.itemView.setOnClickListener{onItemClickCallback.onItemClicked(listOfGithubUser[holder.adapterPosition])}

    }

    override fun getItemCount(): Int = listOfGithubUser.size
}