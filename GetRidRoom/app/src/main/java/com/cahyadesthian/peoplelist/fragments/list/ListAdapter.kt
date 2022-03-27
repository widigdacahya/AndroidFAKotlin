package com.cahyadesthian.peoplelist.fragments.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.cahyadesthian.peoplelist.R
import com.cahyadesthian.peoplelist.model.User
import com.cahyadesthian.peoplelist.databinding.UserRowBinding

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()

    inner class MyViewHolder(val listItemBinding: UserRowBinding) : RecyclerView.ViewHolder(listItemBinding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = UserRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.listItemBinding.apply {
            tvIdUserData.text = currentItem.id.toString()
            tvFirstNameData.text = currentItem.firstName
            tvLastNameData.text = currentItem.lastName
            tvAgeData.text = currentItem.age.toString()
            cvUserItem.setOnClickListener {
                val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
                Navigation.findNavController(it).navigate(action)
            }
        }


    }

    override fun getItemCount(): Int = userList.size

    fun setData(user: List<User>) {
        this.userList = user
        notifyDataSetChanged()
    }

}