package com.example.shoplist.Domain

class GetShopItemUseCase(private  val itemRepository: ItemRepository) {
    fun getShoppingItem(shoppingItemID: Int):ShoppingItem
    {
      return  itemRepository.getShoppingItem(shoppingItemID)

    }
}