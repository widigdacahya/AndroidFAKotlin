package com.example.myrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListHeroAdapter(private val listHero: ArrayList<Hero>) : RecyclerView.Adapter<ListHeroAdapter.ListViewHolder>() {

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgHeroPhoto : ImageView = itemView.findViewById(R.id.img_item_hero_photo_UI)
        var tvHeroName : TextView = itemView.findViewById(R.id.tv_item_hero_name_UI)
        var tvHeroDesc : TextView = itemView.findViewById(R.id.tv_item_hero_desc_UI)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_hero, parent,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (heroName,heroDesc,heroPhoto) = listHero[position]
        holder.imgHeroPhoto.setImageResource(heroPhoto)
        holder.tvHeroDesc.text = heroDesc
        holder.tvHeroName.text = heroName
    }

    override fun getItemCount(): Int = listHero.size
}