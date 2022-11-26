package com.example.shoplist.Prisentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.shoplist.Domain.ShoppingItem
import com.example.shoplist.R
import com.google.android.material.textfield.TextInputLayout

class ShoppingItemActivity : AppCompatActivity() {
    /*private lateinit var editViewModel: ShoppingItemViewModel
    private lateinit var tilName: TextInputLayout
    private lateinit var tilCount: TextInputLayout
    private lateinit var editName: EditText
    private lateinit var editCount: EditText
    private lateinit var buttonSave: Button*/
    private var screenMod = EXTRA_UNKNOWN
    private var shopItemId: Int = ShoppingItem.UNDEFINE_ID
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_shopping_item)
        parseIntent()

        val fragment =   when (screenMod) {
                EXTRA_CHANGE -> ShopItemFragment.newInstanceChangeItem(shopItemId)
                EXTRA_ADD -> ShopItemFragment.newInstanceAddItem()
                else -> throw RuntimeException("UNKNOWN screen Mode")
            }

        supportFragmentManager.beginTransaction()
            .add(R.id.shop_item_container,fragment)
            .commit()
  /*          editViewModel.errorInputCount.observe(this)
            {
                if (it) {
                    tilCount.error = "Wrong numeric format"
                } else
                    tilCount.error = null
            }
            editViewModel.errorInputName.observe(this)
            {
                if (it) {
                    tilName.error = "Wrong text format"
                } else
                    tilName.error = null
            }
            editViewModel.isItClose.observe(this)
            {
                finish()
            }*/

    }/*

    private fun launchChangeMod() {


        editViewModel.getShopItem(shopItemId)

        editName.addTextChangedListener { editViewModel.resetErrorName() }
        editCount.addTextChangedListener { editViewModel.resetErrorName() }
        editViewModel.shopItemLd.observe(this) {

            editName.setText(it.name)
            editCount.setText(it.count.toString())

        }
        buttonSave.setOnClickListener {
            editViewModel.changeShopItem(editName.text?.toString(), editCount.text?.toString())

        }
    }

    private fun launchAddMod() {
        buttonSave.setOnClickListener {
            editViewModel.addShopItem(editName.text?.toString(), editCount.text?.toString())

        }
    }
*/
    private fun parseIntent() {
        if (!intent.hasExtra(EXTRA_MODE)) {
            throw RuntimeException("Param MODE is absent")
        }
        var mod = intent.getStringExtra(EXTRA_MODE)
        if (mod != EXTRA_ADD && mod != EXTRA_CHANGE) {
            throw RuntimeException("Cant identify MODE")
        }
        screenMod = mod
        if (screenMod == EXTRA_CHANGE) {
            if (!intent.hasExtra(EXTRA_SHOP_ITEM_ID)) {
                throw RuntimeException("No id in intent")
            } else {
                shopItemId = intent.getIntExtra(EXTRA_SHOP_ITEM_ID, ShoppingItem.UNDEFINE_ID)
            }
        }
    }

    /*private fun initViews() {
        tilName = findViewById(R.id.til_name)
        tilCount = findViewById(R.id.til_count)
        editName = findViewById(R.id.et_name)
        editCount = findViewById(R.id.et_count)
        buttonSave = findViewById(R.id.saveButton)
    }*/






    companion object {
        private const val EXTRA_MODE = "extra_mode"
        private const val EXTRA_SHOP_ITEM_ID = "extra_shop_item"
        private const val EXTRA_ADD = "mode_add"
        private const val EXTRA_CHANGE = "mode_change"
        private const val EXTRA_UNKNOWN = ""
        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, ShoppingItemActivity::class.java)
            intent.putExtra(EXTRA_MODE, EXTRA_ADD)
            return intent
        }

        fun newIntentChangeItem(context: Context, id: Int): Intent {
            val intent = Intent(context, ShoppingItemActivity::class.java)

            intent.putExtra(EXTRA_MODE, EXTRA_CHANGE)
            intent.putExtra(EXTRA_SHOP_ITEM_ID, id)
            return intent
        }
    }


}
