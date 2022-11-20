package com.example.shoplist.Prisentation

import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.shoplist.Domain.DeleteShoppingItemUseCase
import com.example.shoplist.Domain.ShoppingItem
import com.example.shoplist.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var shopListAdapter: ShopListAdapter
    private var count =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var canvas:Canvas
        var draw:Drawable
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this){

        shopListAdapter.shopList = it



        }
        
    }
    private fun setupRecyclerView()
    {
        val  rcViewShopList =findViewById<RecyclerView>(R.id.RecyV)
        shopListAdapter = ShopListAdapter()
        val swipeAndDel = object : SwipeAndDel() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = shopListAdapter.shopList[viewHolder.adapterPosition]
                viewModel.deleteShopItemInShopList(item)
            }

        }
        val itemTouchHelper = ItemTouchHelper(swipeAndDel)
        shopListAdapter.onSwipeShoppingItem = {
            viewModel.deleteShopItemInShopList(it)
        }
        itemTouchHelper.attachToRecyclerView(rcViewShopList)
        rcViewShopList.adapter = shopListAdapter
        rcViewShopList.recycledViewPool.setMaxRecycledViews(ShopListAdapter.ACTIVE_ITEM,5)
        rcViewShopList.recycledViewPool.setMaxRecycledViews(ShopListAdapter.DISABLE_ITEM,5)
        shopListAdapter.onShopItemLongClickListener = {
        viewModel.changeShoppingItem(it)
        }
        shopListAdapter.onShoppingItemClickListener = {
            viewModel.editShoppingItem(it)

        }

    }
    /*private fun showItemList(list: List<ShoppingItem>) {
        linearLayout.removeAllViews()
        for ( item in list)
        {
            val layoutId  = if(item.isActive)
            {
                R.layout.item_active
            }
            else
                R.layout.item_desable

            val view = LayoutInflater.from(this).inflate(layoutId,linearLayout,false)
            val itemName = view.findViewById<TextView>(R.id.item_name_a)
            val itemCount = view.findViewById<TextView>(R.id.count_item_a)

            linearLayout.addView(view)
        }

    }*/
}