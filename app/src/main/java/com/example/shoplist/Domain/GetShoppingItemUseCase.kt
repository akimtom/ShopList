package com.example.shoplist.Domain

class GetShoppingItemListUseCase(private val itemRepository: ItemRepository) {

    fun getShoppingList():List<ShoppingItem>
    {
       return itemRepository.getShoppingList()
    }
}