package com.example.shoplist.Prisentation

import android.content.Intent
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
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


    fun changeShoppingItem( shoppingItem: ShoppingItem)
    {

      val  newShoppingItem = shoppingItem.copy(isActive = !shoppingItem.isActive)
        changeShoppingItemUse.changeShoppingItem(newShoppingItem)

    }




}