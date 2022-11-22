package com.example.shoplist.Prisentation

import androidx.recyclerview.widget.DiffUtil
import com.example.shoplist.Domain.ShoppingItem

class DiffUtilCallBeckItem:DiffUtil.ItemCallback<ShoppingItem>() {
    override fun areItemsTheSame(oldItem: ShoppingItem, newItem: ShoppingItem): Boolean {
       return  oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ShoppingItem, newItem: ShoppingItem): Boolean {
       return  oldItem==newItem
    }
}