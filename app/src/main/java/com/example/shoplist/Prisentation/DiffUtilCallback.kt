package com.example.shoplist.Prisentation

import androidx.recyclerview.widget.DiffUtil
import com.example.shoplist.Domain.ShoppingItem

class DiffUtilCallback(private val oldList: List<ShoppingItem>,

                       private val newList:List<ShoppingItem>):DiffUtil.Callback()
{
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id==newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition]==newList[newItemPosition]
    }

}