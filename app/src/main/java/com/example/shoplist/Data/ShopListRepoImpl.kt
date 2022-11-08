package com.example.shoplist.Data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoplist.Domain.ItemRepository
import com.example.shoplist.Domain.ShoppingItem

object ShopListRepoImpl:ItemRepository {

    private var autoIncrementId  = 0
    private val shopListLD = MutableLiveData<List<ShoppingItem>>()
    private val shopList= mutableListOf<ShoppingItem>()
    override fun addShoppingItem(shoppingItem: ShoppingItem) {
        if(shoppingItem.id == ShoppingItem.UNDEFINE_ID) {
            shoppingItem.id = autoIncrementId++
        }
        shopList.add(shoppingItem)
        updateList()
    }
    init {
        for (i in 0 until 10)
        {
            val item = ShoppingItem("Name - $i",i, true)
            addShoppingItem(item)
        }
    }

    override fun changeShoppingItem(shoppingItem: ShoppingItem) {
        val oldShopItemElement  = getShoppingItem(shoppingItem.id)
        shopList.remove(oldShopItemElement)
        addShoppingItem(shoppingItem)

    }

    override fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shopList.remove(shoppingItem)
        updateList()
    }

    override fun getShoppingItem(shoppingItemID: Int): ShoppingItem {
        return shopList.find { it.id==shoppingItemID } ?:
        throw RuntimeException("Id $shoppingItemID in shopping list not find ")
    }

    override fun getShoppingList(): LiveData<List<ShoppingItem>> {
        return shopListLD
    }
    fun updateList()
    {
        shopListLD.value = shopList.toList()
    }
}