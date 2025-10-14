package com.example.android108

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android108.databinding.TitleItemBinding

class ItemAdapter(
    private val onClick: (Item) -> Unit,
    private val setSelectedMode: (Boolean) -> Unit
): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    var selectedMode = false

    private val diffCallBack = object : DiffUtil.ItemCallback<Item>(){
        override fun areItemsTheSame(
            oldItem: Item,
            newItem: Item
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: Item,
            newItem: Item
        ): Boolean {
            return oldItem == newItem
        }
    }
    val diffUtil = AsyncListDiffer(this, diffCallBack)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val binding = TitleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return diffUtil.currentList.size
    }

    override fun onBindViewHolder(
        holder: ItemViewHolder,
        position: Int
    ) {
        val item = diffUtil.currentList[position]
        holder.bind(item)
    }

    inner class ItemViewHolder(private val binding: TitleItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Item){
            binding.title.text = item.title
//            itemView.setOnClickListener {
//                onClick(item)
//            }
            binding.checkBox.visibility = if (selectedMode) View.VISIBLE else View.GONE
            binding.checkBox.isChecked = item.isSelected
            binding.root.setOnLongClickListener {
                if (!selectedMode) {
                    toggleSelection(true)

                }

                true
            }

        }
    }

    fun toggleSelectedItem(position: Int){
        val item = diffUtil.currentList[position]
        val newItem = item.copy(  isSelected = !item.isSelected )
    }

    fun toggleSelection(enable: Boolean){
        selectedMode = enable
        setSelectedMode(enable)
        notifyDataSetChanged()
    }

    fun submitList(list: List<Item>){
        diffUtil.submitList(list)
    }
}

