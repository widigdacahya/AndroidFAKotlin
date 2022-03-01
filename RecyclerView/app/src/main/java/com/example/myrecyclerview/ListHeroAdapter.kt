package com.example.myrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myrecyclerview.databinding.ItemRowHeroBinding

class ListHeroAdapter(private val listHero: ArrayList<Hero>) : RecyclerView.Adapter<ListHeroAdapter.ListViewHolder>() {

    class ListViewHolder(var binding: ItemRowHeroBinding) : RecyclerView.ViewHolder(binding.root) {
        //[2. after got error on eturn onCreateViewHolder, delete below, change formal parameter]
        //var imgHeroPhoto : ImageView = itemView.findViewById(R.id.img_item_hero_photo_UI)
        //var tvHeroName : TextView = itemView.findViewById(R.id.tv_item_hero_name_UI)
        //var tvHeroDesc : TextView = itemView.findViewById(R.id.tv_item_hero_desc_UI)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        //[1. change to use view binnding]
        //val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_hero, parent,false)
        val binding = ItemRowHeroBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (heroName,heroDesc,heroPhoto) = listHero[position]

        //holder.imgHeroPhoto.setImageResource(heroPhoto)

        //[data photo with glide]
        Glide.with(holder.itemView.context)
            .load(heroPhoto) //url gambar
            .circleCrop()   //img jadi lingkaran
            .into(holder.binding.imgItemHeroPhotoUI)  //imageView tempat implementasi

        holder.binding.tvItemHeroDescUI.text = heroDesc
        holder.binding.tvItemHeroNameUI.text = heroName


        /*
        * when an item clicked
        * we can do something here
        * */
        //holder.itemView.setOnClickListener{
        //    Toast.makeText(holder.itemView.context, "\uD83D\uDC4B You click on " + listHero[holder.adapterPosition].name, Toast.LENGTH_SHORT).show()
        //}
        //[the click listener then will be try to implemented from MainActivty]
        holder.itemView.setOnClickListener {onItemClickCallback.onItemClicked(listHero[holder.adapterPosition])}

    }

    override fun getItemCount(): Int = listHero.size



    /*
    * TO MAKE ITEM CLICK LISTENER TRIGERRED
    * FROM MAIN ACTIVITY
    * CODE BELOW
    * */
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: Hero)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

}