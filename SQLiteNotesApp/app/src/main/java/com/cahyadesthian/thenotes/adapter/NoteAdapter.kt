package com.cahyadesthian.thenotes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cahyadesthian.thenotes.R
import com.cahyadesthian.thenotes.databinding.ItemNoteBinding
import com.cahyadesthian.thenotes.entity.Note


//[STEp 5]
/*
MEMBUAT ADAPTER KEK BIASANYA TU ADA IMPLEMENT MEMEBR
YANG SEBELUMNYA MEMBUAT INNER CLASS
*
* */
class NoteAdapter(private val onItemClickCallback: OnItemClickCallback) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    //[step 6]
    /*
    * Setelah itu, generate getter untuk arraylist-nya dan juga constructor
    * untuk interface OnItemClickCallback. Interface ini dibutuhkan karena kita
    * ingin membaca hasil klik pada item RecyclerView di MainActivity.
    * */
    var listNotes = ArrayList<Note>()
        set(listNotes) {
            if(listNotes.size > 0) {
                this.listNotes.clear()
            }
            this.listNotes.addAll(listNotes)
        }
    //[step 6]

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemNoteBinding.bind(itemView)

        fun bind(note:Note) {
            binding.tvItemDateItemUI.text = note.date
            binding.tvItemTitledataItemUI.text = note.title
            binding.tvItemDescriptionItemUI.text = note.description
            binding.cardlayoutItemUI.setOnClickListener{
                onItemClickCallback.onItemClicked(note,adapterPosition)
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note,parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(listNotes[position])
    }

    override fun getItemCount(): Int = this.listNotes.size


    //[step 6]
    interface OnItemClickCallback {
        fun onItemClicked(selectedNote: Note?, position: Int?)
    }
    //[step 6]


    /*
    * Lalu, buat 3 metode untuk menambahkan,
    * memperbarui dan menghapus Item di RecyclerView.
    * */
    //[step 7]
    fun addItem(note: Note) {
        this.listNotes.add(note)
        notifyItemInserted(this.listNotes.size - 1)
    }

    fun updateItem(position: Int, note: Note) {
        this.listNotes[position] = note
        notifyItemChanged(position,note)
    }

    fun removeItem(position: Int) {
        this.listNotes.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, this.listNotes.size)
    }
    //[step 7]
    /*
    * [STEP 8]
    * Lalu mulai tambahkan kode ke masing masing metode tersebut,
    * mulai dari onCreateViewHolder, onBindViewHolder, sampai
    * getItemCount serta class NoteViewHolder.
    * */


}