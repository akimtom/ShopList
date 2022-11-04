package com.example.shoplist.Domain

data class ShoppingItem(
    val name: String,
    val count:Int,
    var isActive:Boolean,
    var id:Int = UNDEFINE_ID,) {
    companion object{
        const val UNDEFINE_ID = -1
    }
}