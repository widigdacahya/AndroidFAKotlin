package com.cahyadesthian.secondsubmissionchy.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.cahyadesthian.secondsubmissionchy.databinding.ItemUserGithubBinding
import com.cahyadesthian.secondsubmissionchy.model.UsersResponse
import com.cahyadesthian.secondsubmissionchy.ui.MainActivity
import com.cahyadesthian.secondsubmissionchy.viewmodel.DetailUserViewModel

class UserMainAdapter : RecyclerView.Adapter<UserMainAdapter.UserMainViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback?=null
    

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    private val list = ArrayList<UsersResponse>()

    fun setList(users: ArrayList<UsersResponse>) {
        list.clear()
        list.addAll(users)
        notifyDataSetChanged()
    }

    inner class  UserMainViewHolder(val binding: ItemUserGithubBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: UsersResponse) {

            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(user)
            }


            binding.apply {
                Glide.with(itemView)
                    .load(user.avatar_url)
                    .centerCrop()
                    .into(ivUserGithubPhotoItemUI)

                tvUserGithubNameUI.text = user.name
                tvUserGithubUsernameUI.text = user.login
                tvCompanyUserGithubUI.text = user.company

                //if(user.company.isEmpty()) {
                //   tvCompanyUserGithubUI.text = "None"
                //} else tvCompanyUserGithubUI.text = user.company

                tvRepoUserGithubUI.text = user.public_repos.toString()

            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserMainAdapter.UserMainViewHolder {
        val view = ItemUserGithubBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UserMainViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserMainViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickCallback{
        fun onItemClicked(data:UsersResponse)
    }
}