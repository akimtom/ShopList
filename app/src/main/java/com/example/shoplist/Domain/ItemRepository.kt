package com.example.shoplist.Domain

interface ItemRepository {
    fun addShoppingItem(shoppingItem: ShoppingItem)

    fun changeShoppingItem(shoppingItem: ShoppingItem)

    fun deleteShoppingItem(shoppingItem:ShoppingItem)

    fun getShoppingItem(shoppingItemID: Int):ShoppingItem

    fun getShoppingList():List<ShoppingItem>
}