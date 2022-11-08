package com.example.shoplist.Domain

import androidx.lifecycle.LiveData

interface ItemRepository {
    fun addShoppingItem(shoppingItem: ShoppingItem)

    fun changeShoppingItem(shoppingItem: ShoppingItem)

    fun deleteShoppingItem(shoppingItem:ShoppingItem)

    fun getShoppingItem(shoppingItemID: Int):ShoppingItem

    fun getShoppingList():LiveData<List<ShoppingItem>>
}