package com.example.android108

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(
    private val itemList: List<Item>,
    private val onClick: (Item) -> Unit
): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.title_item, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(
        holder: ItemViewHolder,
        position: Int
    ) {
        val item = itemList[position]
        holder.bind(item)
    }

    inner class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val titleText = itemView.findViewById<TextView>(R.id.title)
        fun bind(item: Item){
            titleText.text = item.title
            itemView.setOnClickListener {
                onClick(item)
            }
        }
    }
}