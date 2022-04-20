package com.cahyadesthian.learnexercisethree.ui.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.cahyadesthian.learnexercisethree.R
import com.cahyadesthian.learnexercisethree.data.model.UserItemResponse
import com.cahyadesthian.learnexercisethree.databinding.ItemRowBinding

/**
 * Adapter for
 * Style Row
 * */

class UserRowRecyclerViewAdapter : RecyclerView.Adapter<UserRowRecyclerViewAdapter.UserViewRowHolder>() {

    //like oldPersonList
    private var oldUserList = ArrayList<UserItemResponse>()

    inner class UserViewRowHolder(val rowBinding: ItemRowBinding) : RecyclerView.ViewHolder(rowBinding.root) {
        fun setThings(user: UserItemResponse) {

            rowBinding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(user)
            }

            rowBinding.apply {
                Glide.with(itemView)
                    .load(user.avatar_url)
                    .placeholder(R.drawable.placeholder_user)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .centerCrop()
                    .into(ivUserItem)

                tvUserloginItem.text = user.login
                tvIduserItem.text = user.id.toString()
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewRowHolder {
        val view = ItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UserViewRowHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewRowHolder, position: Int) {
        holder.setThings(oldUserList[position])
    }

    override fun getItemCount(): Int = oldUserList.size

    fun setData(newUserList: ArrayList<UserItemResponse>) {
        val diffUtil = TheDiffUtil(oldUserList, newUserList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        oldUserList = newUserList
        diffResult.dispatchUpdatesTo(this)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: UserItemResponse)
    }

    private var onItemClickCallback: OnItemClickCallback?= null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

}