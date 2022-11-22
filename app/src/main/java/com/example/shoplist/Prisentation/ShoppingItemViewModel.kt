package com.example.shoplist.Prisentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoplist.Data.ShopListRepoImpl
import com.example.shoplist.Domain.AddShoppingItemUseCase
import com.example.shoplist.Domain.ChangeShoppingItemUseCase
import com.example.shoplist.Domain.GetShopItemUseCase
import com.example.shoplist.Domain.ShoppingItem
import java.lang.Exception

class ShoppingItemViewModel:ViewModel() {
    private val repository = ShopListRepoImpl
    val getShopItemUseCase = GetShopItemUseCase(repository)
    val addShoppingItemUseCase = AddShoppingItemUseCase(repository)
    val changeShoppingItemUseCase = ChangeShoppingItemUseCase(repository)

    private val _errorInputName = MutableLiveData<Boolean>()

  private val errorInputName:LiveData<Boolean>
    get() = _errorInputName

  private  val _errorInputCount = MutableLiveData<Boolean>()
     private val errorInputCount : LiveData<Boolean>
    get() = _errorInputCount

    private val  _shopItem = MutableLiveData<ShoppingItem>()
    val shopItemLd:LiveData<ShoppingItem>
    get() = _shopItem

    private val _isItClose =MutableLiveData<Unit>()
    val isItClose:LiveData<Unit>
    get() = _isItClose

    fun getShopItem(id:Int)
    {
        val item =  getShopItemUseCase.getShoppingItem(id)

        _shopItem.value =  item
    }
    fun addShopItem( inputName:String?,inputCount:String? )
    {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name,count)
        if (fieldsValid) {
            val shoppingItem = ShoppingItem(name, count, isActive = true)
            addShoppingItemUseCase.addShoppingItem(shoppingItem)
            finishing()
        }
    }
    fun changeShopItem(inputName:String?,inputCount:String?)
    {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name,count)
        if (fieldsValid) {
             _shopItem.value?.let {
                 val item = it.copy(name = name, count = count)
                 changeShoppingItemUseCase.changeShoppingItem(item)
                 finishing()  }

        }
    }

    private fun parseName(iName:String?):String
    {
        return iName?.trim()?:""
    }

    private fun parseCount(iCount:String?):Int {
        return try {
            iCount?.trim()?.toInt()?:0
        }
        catch (E:Exception)
        {
            0
        }

    }
    private fun validateInput(name:String,count:Int):Boolean
    {
        var result = true
        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        if(count<=0) {
            _errorInputCount.value = true
            result = false
        }
        return  result
    }
    public fun resetErrorName()
    {
        _errorInputName.value=false

    }
    public fun resetErrorCount()
    {
        _errorInputCount.value = false
    }


    public fun finishing()
    {
        _isItClose.value = Unit
    }
}