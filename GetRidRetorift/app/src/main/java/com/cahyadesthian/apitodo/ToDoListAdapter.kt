package com.cahyadesthian.apitodo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cahyadesthian.apitodo.databinding.ItemTodoBinding

class ToDoListAdapter : RecyclerView.Adapter<ToDoListAdapter.ToDoViewHolder>() {

    inner class ToDoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)


    private val diffCallback = object: DiffUtil.ItemCallback<ToDo>() {
        override fun areItemsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var todos: List<ToDo>
        get() = differ.currentList
        set(value) {differ.submitList(value)}

    override fun getItemCount(): Int = todos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(ItemTodoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    //set data
    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.binding.apply {
            val todo = todos[position]
            tvUserIdDataItemUI.text = todo.userId.toString()
            tvTitleItemUI.text = todo.title
            cbTodoItemUI.isChecked = todo.completed
        }
    }




}