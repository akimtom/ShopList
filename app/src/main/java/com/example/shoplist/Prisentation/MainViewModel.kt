package com.example.shoplist.Prisentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoplist.Data.ShopListRepoImpl
import com.example.shoplist.Domain.*


class MainViewModel: ViewModel() {
    private val repository = ShopListRepoImpl

    private val getShopListUseCase = GetShoppingItemListUseCase(repository)
    private val deleteShoppingItemUse = DeleteShoppingItemUseCase(repository)
    private val changeShoppingItemUse = ChangeShoppingItemUseCase(repository)

    val shopList = getShopListUseCase.getShoppingList()



    fun deleteShopItemInShopList( shoppingItem: ShoppingItem)
    {

        deleteShoppingItemUse.deleteShoppingItem(shoppingItem)

    }

    //Возможно сдесь написан Бред
    fun changeShoppingItem(shoppingItem: ShoppingItem)
    {

        shoppingItem.isActive = !shoppingItem.isActive
        changeShoppingItemUse.changeShoppingItem(shoppingItem)

    }



}