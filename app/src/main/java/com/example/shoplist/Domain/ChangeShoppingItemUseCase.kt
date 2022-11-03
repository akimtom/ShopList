package com.example.shoplist.Domain

class ChangeShoppingItemUseCase(private  val itemRepository: ItemRepository) {
    fun changeShoppingItem(shoppingItem: ShoppingItem)
    {
        itemRepository.changeShoppingItem(shoppingItem)
    }
}