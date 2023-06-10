package com.emircankirez.multityperecyclerviewsamedata.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emircankirez.multityperecyclerviewsamedata.databinding.CallRowBinding
import com.emircankirez.multityperecyclerviewsamedata.databinding.EmailRowBinding
import com.emircankirez.multityperecyclerviewsamedata.model.Item
import com.emircankirez.multityperecyclerviewsamedata.model.ItemType

class ItemAdapter (private val itemList : ArrayList<Item>, private val listener : OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    interface OnItemClickListener{
        fun onItemClick(position: Int)
        fun onItemLongClick(position: Int)
    }

    class ItemCallHolder(private val binding: CallRowBinding, private val listener: OnItemClickListener) : RecyclerView.ViewHolder(binding.root){
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if(position != RecyclerView.NO_POSITION)
                    listener.onItemClick(position)
            }

            itemView.setOnLongClickListener {
                val position = adapterPosition
                if(position != RecyclerView.NO_POSITION)
                    listener.onItemLongClick(position)
                true
            }
        }

        fun bind(item : Item){
            binding.tvContent.text = item.content
        }
    }

    class ItemEmailHolder(private val binding: EmailRowBinding, private val listener: OnItemClickListener) : RecyclerView.ViewHolder(binding.root){
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if(position != RecyclerView.NO_POSITION)
                    listener.onItemClick(position)
            }

            itemView.setOnLongClickListener {
                val position = adapterPosition
                if(position != RecyclerView.NO_POSITION)
                    listener.onItemLongClick(position)
                true
            }
        }

        fun bind(item : Item){
            binding.tvContent.text = item.content
        }
    }

    override fun getItemViewType(position: Int): Int {
        return itemList[position].viewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            ItemType.CALL.ordinal -> {
                val binding = CallRowBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ItemCallHolder(binding, listener)
            }
            ItemType.EMAIL.ordinal -> {
                val binding = EmailRowBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ItemEmailHolder(binding, listener)
            }
            else -> throw IllegalArgumentException("Invalid viewType")
        }
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = itemList[position]
        when(item.viewType){
            ItemType.CALL.ordinal -> {
                (holder as ItemCallHolder).bind(item)
            }
            ItemType.EMAIL.ordinal -> {
                (holder as ItemEmailHolder).bind(item)
            }
        }
    }

}