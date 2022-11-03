package com.example.shoplist.Domain

class AddShoppingItemUseCase(private  val itemRepository: ItemRepository) {

    fun addShoppingItem(shoppingItem: ShoppingItem)
    {
        itemRepository.addShoppingItem(shoppingItem)
    }
}