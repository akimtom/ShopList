package com.example.shoplist.Data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shoplist.Domain.ShoppingItem
@Entity(tableName = "shop_items")
data class ShopItemDbModel(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val name: String,
    val count:Int,
    var isActive:Boolean,

) {
}