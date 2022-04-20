package com.cahyadesthian.learnexercisethree.ui.adapter
import androidx.recyclerview.widget.DiffUtil
import com.cahyadesthian.learnexercisethree.data.model.UserItemResponse

/**
 * It said that better
 * practice foe notifysetchanged thing
 * make recycler view perform better
 * */

class TheDiffUtil(
    private val oldUserList : List<UserItemResponse>,
    private val newUserList : List<UserItemResponse>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldUserList.size
    override fun getNewListSize(): Int = newUserList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldUserList[oldItemPosition].id == newUserList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldUserList[oldItemPosition].id != newUserList[newItemPosition].id -> {
                false
            }
            oldUserList[oldItemPosition].login != newUserList[newItemPosition].login -> {
                false
            }
            oldUserList[oldItemPosition].avatar_url != newUserList[newItemPosition].avatar_url -> {
                false
            }
            else -> true
        }
    }
}