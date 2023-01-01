package com.example.shoplist.Data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shoplist.Domain.ShoppingItem

@Dao
interface ShopListDao {
    @Query("select * from shop_items")
    fun getShopList():LiveData<List<ShopItemDbModel>>

    @Insert(onConflict=OnConflictStrategy.REPLACE)
    fun addShopItem(shopItemDbModel: ShopItemDbModel)

    @Query("DELETE from shop_items where id=:shopItemId")
    fun deleteShopItem(shopItemId :Int)

    @Query("select * from shop_items where id=:shopItemId LIMIT 1")
    fun getShopItem(shopItemId :Int):LiveData<ShopItemDbModel>

}