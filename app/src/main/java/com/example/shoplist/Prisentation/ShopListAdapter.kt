package com.example.shoplist.Prisentation

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shoplist.Domain.ShoppingItem
import com.example.shoplist.R

class ShopListAdapter() :


    ListAdapter<ShoppingItem,ShopListAdapter.ShopItemViewHolder>(DiffUtilCallBeckItem()) {


    var onShopItemLongClickListener: ((ShoppingItem) -> Unit?)? = null
    var onShoppingItemClickListener:((ShoppingItem)->Unit?)? = null
    var onSwipeShoppingItem:((ShoppingItem)->Unit?)?=null

    inner class ShopItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName = itemView.findViewById<TextView>(R.id.item_name_a)
        val itemCount = itemView.findViewById<TextView>(R.id.count_item_a)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        Log.d("VType","view Type =  $viewType")
        val view = if (viewType==ACTIVE_ITEM) LayoutInflater.from(parent.context).inflate(
            R.layout.item_active,
            parent,
            false
        )
        else

                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_desable,
                        parent,
                        false
        )
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
        val status = if(shopItem.isActive)
            "Active"
        else
            "Disable"
        holder.apply {


            itemName.text = shopItem.name + " " + status
            itemCount.text = shopItem.count.toString()
        }
        holder.itemView.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }
        holder.itemView.setOnClickListener{
            onShoppingItemClickListener?.invoke(shopItem)

        }




    }

    override fun getItemViewType(position: Int): Int {
        return if(getItem(position).isActive)
            ACTIVE_ITEM
        else
            DISABLE_ITEM

    }




    companion object{
       const val ACTIVE_ITEM = 1
       const val DISABLE_ITEM  = 2
    }
}