package com.example.shoplist.Domain

class DeleteShoppingItemUseCase(private  val itemRepository: ItemRepository) {
    fun deleteShoppingItem(shoppingItem:ShoppingItem)
    {
        itemRepository.deleteShoppingItem(shoppingItem)
    }
}