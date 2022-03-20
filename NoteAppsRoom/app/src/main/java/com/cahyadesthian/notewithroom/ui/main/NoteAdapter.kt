package com.cahyadesthian.notewithroom.ui.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cahyadesthian.notewithroom.database.Note
import com.cahyadesthian.notewithroom.databinding.ItemNoteBinding
import com.cahyadesthian.notewithroom.helper.NoteDiffCallback
import com.cahyadesthian.notewithroom.ui.insert.NoteAddUpdateActivity

/**
 * STEP 19
 * */

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {


    private val listNotes = ArrayList<Note>()
    fun setListNotes(listNotes: List<Note>) {
        val diffCallback = NoteDiffCallback(this.listNotes, listNotes)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listNotes.clear()
        this.listNotes.addAll(listNotes)
        diffResult.dispatchUpdatesTo(this)
    }


    inner class NoteViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            with(binding) {
                tvItemDateItemUI.text = note.date
                tvItemDescriptionItemUI.text = note.description
                tvItemTitledataItemUI.text = note.title
                cardlayoutItemUI.setOnClickListener{
                    val intent = Intent(it.context, NoteAddUpdateActivity::class.java)
                    intent.putExtra(NoteAddUpdateActivity.EXTRA_NOTE, note)
                    it.context.startActivity(intent)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(listNotes[position])
    }

    override fun getItemCount(): Int = listNotes.size
}