package com.cahyadesthian.learnexercisethree.ui.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.cahyadesthian.learnexercisethree.R
import com.cahyadesthian.learnexercisethree.data.model.UserItemResponse
import com.cahyadesthian.learnexercisethree.databinding.ItemGridBinding


/**
 * Adapter for
 * Grid Row
 * */

class UserGridRecyclerViewAdapter : RecyclerView.Adapter<UserGridRecyclerViewAdapter.UserGridViewHolder>() {

    inner class UserGridViewHolder(val gridBinding: ItemGridBinding) : RecyclerView.ViewHolder(gridBinding.root) {
        fun setThings(user: UserItemResponse) {

            gridBinding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(user)
            }

            gridBinding.apply {
                Glide.with(itemView)
                    .load(user.avatar_url)
                    .placeholder(R.drawable.placeholder_user)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .centerCrop()
                    .into(ivUserItemUI)

                tvUsernameItemUI.text = user.login
            }
        }

    }

    //like oldPersonList
    private var oldUserList = ArrayList<UserItemResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserGridViewHolder {
        val view = ItemGridBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UserGridViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserGridViewHolder, position: Int) {
        holder.setThings(oldUserList[position])
    }

    override fun getItemCount(): Int = oldUserList.size

    interface OnItemClickCallback {
        fun onItemClicked(data: UserItemResponse)
    }

    private var onItemClickCallback: OnItemClickCallback?= null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setData(newUserList: ArrayList<UserItemResponse>) {
        val diffUtil = TheDiffUtil(oldUserList, newUserList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        oldUserList = newUserList
        diffResult.dispatchUpdatesTo(this)
    }
}