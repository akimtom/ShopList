package com.example.shoplist.Prisentation

import android.content.Intent
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

import com.example.shoplist.Prisentation.ShoppingItemActivity.Companion.newIntentChangeItem
import com.example.shoplist.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var shopListAdapter: ShopListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setupRecyclerView()
        val buttonAddShoppingItem = findViewById<FloatingActionButton>(R.id.add_item_button)
        buttonAddShoppingItem.setOnClickListener {
            val intent = ShoppingItemActivity.newIntentAddItem(this)
            startActivity(intent)
        }
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this){

        shopListAdapter.submitList( it)



        }
        setupItemClickListener()
        
    }
    private fun setupRecyclerView()
    {
        val  rcViewShopList =findViewById<RecyclerView>(R.id.RecyV)
        shopListAdapter = ShopListAdapter()
        val swipeAndDel = object : SwipeAndDel() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = shopListAdapter.currentList[viewHolder.adapterPosition]
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



    }
    private fun  setupItemClickListener()
    {
        shopListAdapter.onShoppingItemClickListener = {
            val intent = newIntentChangeItem(this,it.id)
           startActivity(intent)
        }
    }


}