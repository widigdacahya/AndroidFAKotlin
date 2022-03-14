package com.cahyadesthian.githubuserchy.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.cahyadesthian.githubuserchy.databinding.ItemUserBinding
import com.cahyadesthian.githubuserchy.model.UserItemsResponse

class UserGridRecyclerViewAdapter : RecyclerView.Adapter<UserGridRecyclerViewAdapter.UserGridViewHolder>() {

    private val listUser = ArrayList<UserItemsResponse>()

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setList(githubUsers: ArrayList<UserItemsResponse>) {
        listUser.clear()
        listUser.addAll(githubUsers)
        notifyDataSetChanged()
    }


    inner class UserGridViewHolder(val itemBindinng: ItemUserBinding) : RecyclerView.ViewHolder(itemBindinng.root) {

        fun resultBinding(userGithub: UserItemsResponse) {
            itemBindinng.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(userGithub)
            }
            itemBindinng.apply {
                Glide.with(itemView)
                    .load(userGithub.avatar_url)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(ivUserItemUI)

                tvUsernameItemUI.text = userGithub.login
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserGridViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UserGridViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserGridViewHolder, position: Int) {
        holder.resultBinding(listUser[position])
    }

    override fun getItemCount(): Int = listUser.size


    interface OnItemClickCallback {
        fun onItemClicked(user: UserItemsResponse)
    }

}