package com.example.shoplist.Domain

import androidx.lifecycle.LiveData

class GetShoppingItemListUseCase(private val itemRepository: ItemRepository) {

    fun getShoppingList():LiveData<List<ShoppingItem>>
    {
       return itemRepository.getShoppingList()
    }
}